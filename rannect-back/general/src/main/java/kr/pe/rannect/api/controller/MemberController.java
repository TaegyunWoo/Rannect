/**
 * author         : 우태균
 * description    : 사용자 관련 컨트롤러
 */
package kr.pe.rannect.api.controller;

import kr.pe.rannect.api.controller.api.MemberApi;
import kr.pe.rannect.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static kr.pe.rannect.api.dto.AuthTokenPairDto.AuthTokenPairResponse;
import static kr.pe.rannect.api.dto.MemberDto.MemberRequest;
import static kr.pe.rannect.api.dto.MemberDto.SignInRequest;

@RequiredArgsConstructor
@RestController
public class MemberController implements MemberApi {
  private final MemberService memberService;
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
    Cookie tokenCookie = new Cookie("Access-Token", tokenCookieVal);
    tokenCookie.setMaxAge(refreshTokenValidMilliSec); //재발급시 만료된 Access Token 값이 필요하기에, 유효기한을 refresh 토큰과 동일하게 설정
    tokenCookie.setHttpOnly(true); //XSS 공격 방지
    tokenCookie.setPath("/");
    response.addCookie(tokenCookie);

    //access token exist cookie
    Cookie existTokenCookie = new Cookie("Exist-Access-Token",
        URLEncoder.encode("true", StandardCharsets.UTF_8)
    );
    existTokenCookie.setPath("/");
    existTokenCookie.setMaxAge(refreshTokenValidMilliSec);
    response.addCookie(existTokenCookie);

    return new ResponseEntity<>(authTokenPairResponse, HttpStatus.OK);
  }
}
