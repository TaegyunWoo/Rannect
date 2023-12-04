/**
 * author         : 우태균
 * description    : 쿠키 관련 유틸리티
 */
package kr.pe.rannect.api.utils.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CookieUtils {
  public static final String ACCESS_TOKEN_COOKIE_NAME = "Access-Token";
  public static final String EXIST_TOKEN_COOKIE_NAME = "Exist-Access-Token";
  public static final String COOKIE_PATH = "/";
  private int refreshTokenValidMilliSec; //Refresh Token 유효시간

  public CookieUtils(@Value("${jwt.refreshTokenValidMilliSec}") int refreshTokenValidMilliSec) {
    this.refreshTokenValidMilliSec = refreshTokenValidMilliSec;
  }

  public void addAuthCookie(HttpServletResponse targetResponse, String accessToken) {
    //access token cookie
    String tokenCookieVal = null;
    tokenCookieVal = URLEncoder.encode("Bearer " + accessToken, StandardCharsets.UTF_8);
    Cookie tokenCookie = new Cookie(ACCESS_TOKEN_COOKIE_NAME, tokenCookieVal);
    tokenCookie.setMaxAge(refreshTokenValidMilliSec); //재발급시 만료된 Access Token 값이 필요하기에, 유효기한을 refresh 토큰과 동일하게 설정
    tokenCookie.setHttpOnly(true); //XSS 공격 방지
    tokenCookie.setPath(COOKIE_PATH);
    targetResponse.addCookie(tokenCookie);

    //access token exist cookie
    Cookie existTokenCookie = new Cookie(EXIST_TOKEN_COOKIE_NAME,
        URLEncoder.encode("true", StandardCharsets.UTF_8)
    );
    existTokenCookie.setPath(COOKIE_PATH);
    existTokenCookie.setMaxAge(refreshTokenValidMilliSec);
    targetResponse.addCookie(existTokenCookie);
  }

  public void removeAuthCookie(HttpServletResponse response) {
    //remove access token cookie
    Cookie tokenCookie = new Cookie(ACCESS_TOKEN_COOKIE_NAME, "");
    tokenCookie.setMaxAge(0);
    tokenCookie.setHttpOnly(true);
    tokenCookie.setPath(COOKIE_PATH);
    response.addCookie(tokenCookie);

    //remove access token exist cookie
    Cookie existTokenCookie = new Cookie(EXIST_TOKEN_COOKIE_NAME, "");
    existTokenCookie.setPath(COOKIE_PATH);
    existTokenCookie.setMaxAge(0);
    response.addCookie(existTokenCookie);
  }
}
