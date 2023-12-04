/**
 * author         : 우태균
 * description    : 인가 관련 컨트롤러
 */
package kr.pe.rannect.api.controller;

import kr.pe.rannect.api.controller.api.AuthApi;
import kr.pe.rannect.api.exception.AuthenticationException;
import kr.pe.rannect.api.exception.ErrorCode;
import kr.pe.rannect.api.service.TokenService;
import kr.pe.rannect.api.utils.http.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static kr.pe.rannect.api.dto.AuthTokenDto.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController implements AuthApi {
  private final TokenService tokenService;
  private final CookieUtils cookieUtils;

  @Override
  public AuthTokenPairResponse reissueAccessToken(ValidRefreshToken validRefreshToken, HttpServletRequest httpServletRequest, HttpServletResponse response) {
    //Access Token 추출
    String reqAccessToken = Arrays.stream(httpServletRequest.getCookies())
        .filter(cookie -> cookie.getName().equals("Access-Token"))
        .map(Cookie::getValue)
        .findFirst()
        .orElseThrow(() -> {
          log.info("[Auth] User({}) requested with no access token", httpServletRequest.getRemoteAddr());
          return new AuthenticationException(ErrorCode.CANNOT_FIND_ACCESS_TOKEN);
        });
    reqAccessToken = URLDecoder.decode(reqAccessToken, StandardCharsets.UTF_8);

    //Bearer로 시작하는지 확인
    if (!reqAccessToken.startsWith("Bearer ")) {
      log.info("[Auth] User({}) sent access token with no 'Bearer' in request header.", httpServletRequest.getRemoteAddr());
      throw new AuthenticationException(ErrorCode.NO_BEARER);
    }
    reqAccessToken = reqAccessToken.substring("Bearer ".length());

    AuthTokenPairResponse authTokenPairResponse = tokenService.reissueAuthToken(reqAccessToken, validRefreshToken.getValidRefreshToken(), httpServletRequest.getRemoteAddr());

    //Access Token 쿠키 추가
    cookieUtils.addAuthCookie(response, authTokenPairResponse.getAccessToken());

    return authTokenPairResponse;
  }

  @Override
  public void deleteAuthCookies(HttpServletResponse response) {
    cookieUtils.removeAuthCookie(response);
  }
}
