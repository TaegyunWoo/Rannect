package kr.pe.rannect.api.utils.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Cookie;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CookieUtilsTest {
  private final int refreshTokenValidMilliSec = 1210000000;
  private CookieUtils cookieUtils;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    cookieUtils = new CookieUtils(refreshTokenValidMilliSec);
  }

  @Test
  void 인가_쿠키_추가() {
    //GIVEN
    String accessTokenVal = "my access token val";
    MockHttpServletResponse response = new MockHttpServletResponse();

    //WHEN
    cookieUtils.addAuthCookie(response, accessTokenVal);

    //THEN
    assertEquals(URLEncoder.encode("Bearer " + accessTokenVal, StandardCharsets.UTF_8), response.getCookie(CookieUtils.ACCESS_TOKEN_COOKIE_NAME).getValue());
    assertEquals("true", response.getCookie(CookieUtils.EXIST_TOKEN_COOKIE_NAME).getValue());
  }

  @Test
  void 인가_쿠키_제거() {
    //GIVEN
    MockHttpServletResponse response = new MockHttpServletResponse();
    response.addCookie(new Cookie(CookieUtils.ACCESS_TOKEN_COOKIE_NAME, "my access token val"));
    response.addCookie(new Cookie(CookieUtils.EXIST_TOKEN_COOKIE_NAME, "my exist access token val"));

    //WHEN
    cookieUtils.removeAuthCookie(response);

    //THEN
    assertTrue(response.getCookie(CookieUtils.ACCESS_TOKEN_COOKIE_NAME).getMaxAge() <= 0);
    assertTrue(response.getCookie(CookieUtils.EXIST_TOKEN_COOKIE_NAME).getMaxAge() <= 0);
  }
}