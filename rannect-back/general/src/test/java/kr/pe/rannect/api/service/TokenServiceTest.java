package kr.pe.rannect.api.service;

import kr.pe.rannect.api.domain.AuthTokenPair;
import kr.pe.rannect.api.repository.AuthTokenPairRepository;
import kr.pe.rannect.api.utils.token.JwtTokenIssuer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static kr.pe.rannect.api.dto.AuthTokenPairDto.AuthTokenPairResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class TokenServiceTest {
  @Mock
  private AuthTokenPairRepository authTokenPairRepository;
  @Mock
  private JwtTokenIssuer tokenIssuer;
  private TokenService tokenService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    tokenService = new TokenService(authTokenPairRepository, tokenIssuer);
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

}