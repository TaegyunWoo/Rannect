/**
 * author         : 우태균
 * description    : 사용자 관련 컨트롤러
 */
package kr.pe.rannect.api.controller;

import kr.pe.rannect.api.controller.api.MemberApi;
import kr.pe.rannect.api.dto.LoginInfo;
import kr.pe.rannect.api.service.MemberService;
import kr.pe.rannect.api.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static kr.pe.rannect.api.dto.AuthTokenDto.AuthTokenPairResponse;
import static kr.pe.rannect.api.dto.MemberDto.MemberRequest;
import static kr.pe.rannect.api.dto.MemberDto.SignInRequest;

@RequiredArgsConstructor
@RestController
public class MemberController implements MemberApi {
  public static final String ACCESS_TOKEN_COOKIE_NAME = "Access-Token";
  public static final String EXIST_TOKEN_COOKIE_NAME = "Exist-Access-Token";
  public static final String COOKIE_PATH = "/";

  private final MemberService memberService;
  private final TokenService tokenService;
  @Value("${jwt.refreshTokenValidMilliSec}")
  private int refreshTokenValidMilliSec; //Refresh Token 유효시간

  @Override
  public void signUp(MemberRequest request) {
    memberService.addNewUser(request);
  }

  @Override
  public ResponseEntity<AuthTokenPairResponse> signIn(SignInRequest request, HttpServletResponse response) {
    AuthTokenPairResponse authTokenPairResponse = memberService.signIn(request);

    //access token cookie
    String tokenCookieVal = null;
    tokenCookieVal = URLEncoder.encode("Bearer " + authTokenPairResponse.getAccessToken(), StandardCharsets.UTF_8);
    Cookie tokenCookie = new Cookie(ACCESS_TOKEN_COOKIE_NAME, tokenCookieVal);
    tokenCookie.setMaxAge(refreshTokenValidMilliSec); //재발급시 만료된 Access Token 값이 필요하기에, 유효기한을 refresh 토큰과 동일하게 설정
    tokenCookie.setHttpOnly(true); //XSS 공격 방지
    tokenCookie.setPath(COOKIE_PATH);
    response.addCookie(tokenCookie);

    //access token exist cookie
    Cookie existTokenCookie = new Cookie(EXIST_TOKEN_COOKIE_NAME,
        URLEncoder.encode("true", StandardCharsets.UTF_8)
    );
    existTokenCookie.setPath(COOKIE_PATH);
    existTokenCookie.setMaxAge(refreshTokenValidMilliSec);
    response.addCookie(existTokenCookie);

    return new ResponseEntity<>(authTokenPairResponse, HttpStatus.OK);
  }

  @Override
  public void signOut(LoginInfo loginInfo, HttpServletResponse response) {
    tokenService.deleteTokenPair(loginInfo.getMemberPk());

    //remove access token cookie
    Cookie tokenCookie = new Cookie(ACCESS_TOKEN_COOKIE_NAME, "");
    tokenCookie.setMaxAge(0);
    tokenCookie.setHttpOnly(true);
    tokenCookie.setPath(COOKIE_PATH);
    response.addCookie(tokenCookie);

    //access token exist cookie
    Cookie existTokenCookie = new Cookie(EXIST_TOKEN_COOKIE_NAME, "");
    existTokenCookie.setPath(COOKIE_PATH);
    existTokenCookie.setMaxAge(0);
    response.addCookie(existTokenCookie);
  }
}
