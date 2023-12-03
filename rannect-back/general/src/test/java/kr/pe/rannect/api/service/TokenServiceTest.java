package kr.pe.rannect.api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClaims;
import kr.pe.rannect.api.domain.AuthTokenPair;
import kr.pe.rannect.api.exception.AuthenticationException;
import kr.pe.rannect.api.exception.ErrorCode;
import kr.pe.rannect.api.repository.AuthTokenPairRepository;
import kr.pe.rannect.api.utils.token.JwtTokenIssuer;
import kr.pe.rannect.api.utils.token.JwtTokenParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static kr.pe.rannect.api.dto.AuthTokenDto.AuthTokenPairResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {
  @Mock
  private AuthTokenPairRepository authTokenPairRepository;
  @Mock
  private JwtTokenIssuer tokenIssuer;
  @Mock
  private JwtTokenParser jwtTokenParser;
  private TokenService tokenService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    tokenService = new TokenService(authTokenPairRepository, tokenIssuer, jwtTokenParser);
  }

  @Test
  void 새_토큰쌍_저장() {
    //GIVEN
    long memberPk = 1L;

    //(mock)
    String accessTokenVal = "my new access token";
    String refreshTokenVal = "my new refresh token";
    given(tokenIssuer.createMemberToken(memberPk, false)).willReturn(accessTokenVal);
    given(tokenIssuer.createMemberToken(memberPk, true)).willReturn(refreshTokenVal);

    //WHEN
    AuthTokenPairResponse result = tokenService.issueNewAuthToken(memberPk);

    //THEN
    then(tokenIssuer).should(times(1)).createMemberToken(memberPk, false);
    then(tokenIssuer).should(times(1)).createMemberToken(memberPk, true);
    then(authTokenPairRepository).should(times(1)).save(any(AuthTokenPair.class));
    assertEquals(accessTokenVal, result.getAccessToken());
    assertEquals(refreshTokenVal, result.getRefreshToken());
  }

  @Test
  void 만료된_Access_토큰과_유효한_Refresh_토큰으로_재발급_요청(@Mock Claims claims, @Mock AuthTokenPair authTokenPair) {
    //GIVEN
    long memberPk = 1L;
    String expiredAccessToken = "my expired access token";
    String validRefreshToken = "my valid refresh token";

    //(mock)
    given(jwtTokenParser.parseJwtToken(validRefreshToken)).willReturn(Optional.of(claims));
    given(jwtTokenParser.parseJwtToken(expiredAccessToken)).willThrow(ExpiredJwtException.class);
    given(claims.get(eq("memberPk"), eq(Long.class))).willReturn(memberPk);
    given(authTokenPairRepository.findById(memberPk)).willReturn(Optional.of(authTokenPair));
    given(authTokenPair.getAccessToken()).willReturn(expiredAccessToken);
    given(authTokenPair.getRefreshToken()).willReturn(validRefreshToken);

    //WHEN-THEN
    assertDoesNotThrow(() -> tokenService.reissueAuthToken(expiredAccessToken, validRefreshToken, null));
  }

  @Test
  void 만료된_Access_토큰과_만료된_Refresh_토큰으로_재발급_요청() {
    //GIVEN
    long memberPk = 1L;
    String expiredAccessToken = "my expired access token";
    String expiredRefreshToken = "my expired refresh token";

    //(mock)
    given(jwtTokenParser.parseJwtToken(expiredRefreshToken)).willThrow(ExpiredJwtException.class);

    //WHEN
    try {
      tokenService.reissueAuthToken(expiredAccessToken, expiredRefreshToken, null);

      //THEN
      fail();
    } catch (AuthenticationException ex) {
      assertSame(ErrorCode.EXPIRED_TOKEN, ex.getErrorCode());
    } catch (Exception ex) {
      fail(ex);
    }
  }

  @Test
  void 만료된_Access_토큰과_비정상_Refresh_토큰으로_재발급_요청() {
    //GIVEN
    long memberPk = 1L;
    String expiredAccessToken = "my expired access token";
    String badRefreshToken = "my bad refresh token";

    //(mock)
    given(jwtTokenParser.parseJwtToken(badRefreshToken)).willThrow(SignatureException.class);

    //WHEN
    try {
      tokenService.reissueAuthToken(expiredAccessToken, badRefreshToken, null);

      //THEN
      fail();
    } catch (AuthenticationException ex) {
      assertSame(ErrorCode.BAD_TOKEN, ex.getErrorCode());
    } catch (Exception ex) {
      fail(ex);
    }
  }

  @Test
  void 만료되지_않은_Access_토큰과_유효한_Refresh_토큰으로_재발급_요청(@Mock Claims claims) {
    //GIVEN
    long memberPk = 1L;
    String validAccessToken = "my valid access token";
    String validRefreshToken = "my valid refresh token";

    //(mock)
    given(jwtTokenParser.parseJwtToken(validRefreshToken)).willReturn(Optional.of(claims));
    given(claims.get(eq("memberPk"), eq(Long.class))).willReturn(memberPk);

    //WHEN
    try {
      tokenService.reissueAuthToken(validAccessToken, validRefreshToken, null);

      //THEN
      fail();
    } catch (AuthenticationException ex) {
      assertSame(ErrorCode.NOT_EXPIRED_ACCESS_TOKEN, ex.getErrorCode());
      then(authTokenPairRepository).should(times(1)).deleteById(eq(memberPk));
    } catch (Exception ex) {
      fail(ex);
    }
  }

  @Test
  void 만료된_Access_토큰과_잘못된_클레임이_담긴_유효한_Refresh_토큰으로_재발급_요청(@Mock Claims claims, @Mock AuthTokenPair currentAuthTokenPair) {
    //GIVEN
    long wrongMemberPk = 1L;
    String expiredAccessToken = "my expired access token before";
    String validRefreshToken = "my valid refresh token before";

    //(mock)
    given(jwtTokenParser.parseJwtToken(validRefreshToken)).willReturn(Optional.of(claims));
    given(jwtTokenParser.parseJwtToken(expiredAccessToken)).willThrow(ExpiredJwtException.class);
    given(claims.get(eq("memberPk"), eq(Long.class))).willReturn(wrongMemberPk);
    given(authTokenPairRepository.findById(wrongMemberPk)).willReturn(Optional.empty());

    //WHEN
    try {
      tokenService.reissueAuthToken(expiredAccessToken, validRefreshToken, null);

      //THEN
      fail();
    } catch (AuthenticationException ex) {
      assertSame(ErrorCode.BAD_TOKEN, ex.getErrorCode());
    } catch (Exception ex) {
      fail(ex);
    }
  }

  @Test
  void 이전에_저장했던_만료된_Access_토큰과_유효한_Refresh_토큰으로_재발급_요청(@Mock Claims claims, @Mock AuthTokenPair currentAuthTokenPair) {
    //GIVEN
    long memberPk = 1L;
    String beforeExpiredAccessToken = "my expired access token before";
    String validRefreshToken = "my valid refresh token before";
    String currentAccessToken = "my access token current";

    //(mock)
    given(jwtTokenParser.parseJwtToken(validRefreshToken)).willReturn(Optional.of(claims));
    given(jwtTokenParser.parseJwtToken(beforeExpiredAccessToken)).willThrow(ExpiredJwtException.class);
    given(claims.get(eq("memberPk"), eq(Long.class))).willReturn(memberPk);
    given(authTokenPairRepository.findById(memberPk)).willReturn(Optional.of(currentAuthTokenPair));
    given(currentAuthTokenPair.getAccessToken()).willReturn(currentAccessToken);

    //WHEN
    try {
      tokenService.reissueAuthToken(beforeExpiredAccessToken, validRefreshToken, null);

      //THEN
      fail();
    } catch (AuthenticationException ex) {
      assertSame(ErrorCode.BAD_TOKEN, ex.getErrorCode());
    } catch (Exception ex) {
      fail(ex);
    }
  }

  @Test
  void 만료된_Access_토큰과_이전에_저장했던_유효한_Refresh_토큰으로_재발급_요청(@Mock Claims claims, @Mock AuthTokenPair currentAuthTokenPair) {
    //GIVEN
    long memberPk = 1L;
    String expiredAccessToken = "my expired access token before";
    String beforeValidRefreshToken = "my valid refresh token before";
    String currentRefreshToken = "my access token current";

    //(mock)
    given(jwtTokenParser.parseJwtToken(beforeValidRefreshToken)).willReturn(Optional.of(claims));
    given(jwtTokenParser.parseJwtToken(expiredAccessToken)).willThrow(ExpiredJwtException.class);
    given(claims.get(eq("memberPk"), eq(Long.class))).willReturn(memberPk);
    given(authTokenPairRepository.findById(memberPk)).willReturn(Optional.of(currentAuthTokenPair));
    given(currentAuthTokenPair.getAccessToken()).willReturn(expiredAccessToken);
    given(currentAuthTokenPair.getRefreshToken()).willReturn(currentRefreshToken);

    //WHEN
    try {
      tokenService.reissueAuthToken(expiredAccessToken, beforeValidRefreshToken, null);

      //THEN
      fail();
    } catch (AuthenticationException ex) {
      assertSame(ErrorCode.BAD_TOKEN, ex.getErrorCode());
    } catch (Exception ex) {
      fail(ex);
    }
  }
}