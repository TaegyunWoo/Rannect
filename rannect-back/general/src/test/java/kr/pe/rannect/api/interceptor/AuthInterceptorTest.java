package kr.pe.rannect.api.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultHeader;
import kr.pe.rannect.api.domain.AuthTokenPair;
import kr.pe.rannect.api.exception.AuthenticationException;
import kr.pe.rannect.api.repository.AuthTokenPairRepository;
import kr.pe.rannect.api.utils.token.JwtTokenParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AuthInterceptorTest {
  @Mock
  HttpServletRequest request;
  @Mock
  private AuthTokenPairRepository authTokenPairRepository;
  @Mock
  private JwtTokenParser jwtTokenParser;
  private AuthInterceptor authInterceptor;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    authInterceptor = new AuthInterceptor(authTokenPairRepository, jwtTokenParser);
  }

  @Test
  void 정상_인가_요청() {
    //GIVEN
    long memberPk = 1L;
    String validAccessToken = "my valid access token";
    Cookie[] cookies = {new Cookie("Access-Token", "Bearer " + validAccessToken)};
    HashMap<String, Object> claimVal = new HashMap<>();
    claimVal.put("memberPk", memberPk);
    Claims claims = new DefaultClaims(claimVal);

    //(mock)
    given(request.getRemoteAddr()).willReturn("request remote address");
    given(request.getCookies()).willReturn(cookies);
    given(jwtTokenParser.parseJwtToken(eq(validAccessToken))).willReturn(Optional.of(claims));
    given(authTokenPairRepository.findById(memberPk)).willReturn(Optional.of(new AuthTokenPair(memberPk, validAccessToken, "my refresh token", "")));

    //WHEN
    boolean result = false;
    try {
      result = authInterceptor.preHandle(request, null, null);
    } catch (Exception e) {
      fail(e);
    }

    //THEN
    then(request).should(times(1)).getRemoteAddr();
    then(request).should(times(2)).getCookies();
    then(jwtTokenParser).should(times(1)).parseJwtToken(eq(validAccessToken));
    then(authTokenPairRepository).should(times(1)).findById(memberPk);
    then(request).should(times(1)).setAttribute(eq("memberPk"), eq(memberPk));
    assertTrue(result);
  }

  @Test
  void 쿠키값이_아예_존재하지_않는_경우() {
    //GIVEN

    //(mock)
    given(request.getCookies()).willReturn(null);

    //WHEN-THEN
    assertThrows(AuthenticationException.class, () -> authInterceptor.preHandle(request, null, null));
    then(request).should(times(1)).getRemoteAddr();
  }

  @Test
  void 쿠키값에_Access_Token이_없는_경우() {
    //GIVEN
    Cookie[] cookies = new Cookie[2];
    cookies[0] = new Cookie("cookie1", "val1");
    cookies[1] = new Cookie("cookie2", "val2");

    //(mock)
    given(request.getCookies()).willReturn(cookies);

    //WHEN-THEN
    assertThrows(AuthenticationException.class, () -> authInterceptor.preHandle(request, null, null));
    then(request).should(times(1)).getRemoteAddr();
  }

  @Test
  void Access_Token이_Bearer로_시작하지_않는_경우() {
    //GIVEN
    String noBearerAccessToken = "no brearer access token";
    Cookie[] cookies = new Cookie[1];
    cookies[0] = new Cookie("Access-Token", noBearerAccessToken);

    //(mock)
    given(request.getCookies()).willReturn(cookies);

    //WHEN-THEN
    assertThrows(AuthenticationException.class, () -> authInterceptor.preHandle(request, null, null));
    then(request).should(times(1)).getRemoteAddr();
  }

  @Test
  void 클레임이_존재하지_않는_토큰인_경우() {
    //GIVEN
    String noClaimsAccessToken = "no claims access token";
    Cookie[] cookies = new Cookie[1];
    cookies[0] = new Cookie("Access-Token", "Bearer " + noClaimsAccessToken);

    //(mock)
    given(request.getCookies()).willReturn(cookies);
    given(jwtTokenParser.parseJwtToken(eq(noClaimsAccessToken))).willReturn(Optional.empty());

    //WHEN-THEN
    assertThrows(AuthenticationException.class, () -> authInterceptor.preHandle(request, null, null));
    then(request).should(times(1)).getRemoteAddr();
  }

  @Test
  void 만료된_토큰인_경우() {
    //GIVEN
    String expiredAccessToken = "expired access token";
    Cookie[] cookies = new Cookie[1];
    cookies[0] = new Cookie("Access-Token", "Bearer " + expiredAccessToken);

    //(mock)
    given(request.getCookies()).willReturn(cookies);
    given(jwtTokenParser.parseJwtToken(eq(expiredAccessToken))).willThrow(new ExpiredJwtException(null, null, null));

    //WHEN-THEN
    assertThrows(AuthenticationException.class, () -> authInterceptor.preHandle(request, null, null));
    then(request).should(times(1)).getRemoteAddr();
  }

  @Test
  void 시그니처가_잘못된_토큰인_경우() {
    //GIVEN
    String wrongSignatureAccessToken = "wrong signature access token";
    Cookie[] cookies = new Cookie[1];
    cookies[0] = new Cookie("Access-Token", "Bearer " + wrongSignatureAccessToken);

    //(mock)
    given(request.getCookies()).willReturn(cookies);
    given(jwtTokenParser.parseJwtToken(eq(wrongSignatureAccessToken))).willThrow(new JwtException(null));

    //WHEN-THEN
    assertThrows(AuthenticationException.class, () -> authInterceptor.preHandle(request, null, null));
    then(request).should(times(1)).getRemoteAddr();
  }

  @Test
  void Redis에_저장되지_않은_토큰인_경우(@Mock Claims claims) {
    //GIVEN
    String notSavedAccessToken = "not saved access token";
    long memberPk = 1l;
    Cookie[] cookies = new Cookie[1];
    cookies[0] = new Cookie("Access-Token", "Bearer " + notSavedAccessToken);

    //(mock)
    given(request.getCookies()).willReturn(cookies);
    given(jwtTokenParser.parseJwtToken(eq(notSavedAccessToken))).willReturn(Optional.of(claims));
    given(claims.get(eq("memberPk"), eq(Long.class))).willReturn(memberPk);
    given(authTokenPairRepository.findById(memberPk)).willReturn(Optional.empty());

    //WHEN-THEN
    assertThrows(AuthenticationException.class, () -> authInterceptor.preHandle(request, null, null));
    then(request).should(times(1)).getRemoteAddr();
  }
}