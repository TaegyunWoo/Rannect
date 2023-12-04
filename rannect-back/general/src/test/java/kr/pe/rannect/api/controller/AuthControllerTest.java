package kr.pe.rannect.api.controller;

import kr.pe.rannect.api.dto.AuthTokenDto;
import kr.pe.rannect.api.exception.AuthenticationException;
import kr.pe.rannect.api.service.TokenService;
import kr.pe.rannect.api.utils.http.CookieUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static kr.pe.rannect.api.dto.AuthTokenDto.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
  @Mock
  private TokenService tokenService;
  @Mock
  private CookieUtils cookieUtils;
  private AuthController authController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    authController = new AuthController(tokenService, cookieUtils);
  }

  @Test
  void AccessToken을_정상적으로_쿠키에_담아_요청한_경우(@Mock HttpServletRequest request, @Mock HttpServletResponse response) {
    //GIVEN
    String accessTokenVal = "my access token val";
    String refreshTokenVal = "my refresh token val";
    Cookie[] cookies = new Cookie[1];
    cookies[0] = new Cookie("Access-Token", URLEncoder.encode("Bearer " + accessTokenVal, StandardCharsets.UTF_8));
    ValidRefreshToken validRefreshToken = ValidRefreshToken.builder()
        .validRefreshToken("my refresh token val")
        .build();
    AuthTokenPairResponse authTokenPairResponse = AuthTokenPairResponse.builder()
        .accessToken(accessTokenVal)
        .build();

    //(mock)
    given(request.getCookies()).willReturn(cookies);
    given(tokenService.reissueAuthToken(anyString(), any(), any())).willReturn(authTokenPairResponse);

    //WHEN
    authController.reissueAccessToken(validRefreshToken, request, response);

    //THEN
    then(tokenService).should(times(1)).reissueAuthToken(eq(accessTokenVal), eq(refreshTokenVal), any());
    then(cookieUtils).should(times(1)).addAuthCookie(eq(response), any());
  }

  @Test
  void 쿠키에_AccessToken가_없는_경우(@Mock HttpServletRequest request, @Mock HttpServletResponse response) {
    //GIVEN
    String refreshTokenVal = "my refresh token val";
    Cookie[] cookies = new Cookie[1];
    cookies[0] = new Cookie("my-foo", "my_foo_val");
    ValidRefreshToken validRefreshToken = ValidRefreshToken.builder()
        .validRefreshToken("my refresh token val")
        .build();

    //(mock)
    given(request.getCookies()).willReturn(cookies);

    //WHEN-THEN
    assertThrows(AuthenticationException.class, () -> authController.reissueAccessToken(validRefreshToken, request, response));
    then(tokenService).should(times(0)).reissueAuthToken(any(), any(), any());
  }

  @Test
  void AccessToken에_Bearer가_없는_경우(@Mock HttpServletRequest request, @Mock HttpServletResponse response) {
    //GIVEN
    String accessTokenVal = "my access token val";
    String refreshTokenVal = "my refresh token val";
    Cookie[] cookies = new Cookie[1];
    cookies[0] = new Cookie("Access-Token", URLEncoder.encode(accessTokenVal, StandardCharsets.UTF_8));
    ValidRefreshToken validRefreshToken = ValidRefreshToken.builder()
        .validRefreshToken("my refresh token val")
        .build();

    //(mock)
    given(request.getCookies()).willReturn(cookies);

    //WHEN-THEN
    assertThrows(AuthenticationException.class, () -> authController.reissueAccessToken(validRefreshToken, request, response));
    then(tokenService).should(times(0)).reissueAuthToken(any(), any(), any());
  }

  @Test
  void 인증_관련_쿠키_제거(@Mock HttpServletResponse response) {
    //GIVEN

    //WHEN
    authController.deleteAuthCookies(response);

    //THEN
    then(cookieUtils).should(times(1)).removeAuthCookie(response);
  }
}