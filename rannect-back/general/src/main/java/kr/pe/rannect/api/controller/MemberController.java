/**
 * author         : 우태균
 * description    : 사용자 관련 컨트롤러
 */
package kr.pe.rannect.api.controller;

import kr.pe.rannect.api.controller.api.MemberApi;
import kr.pe.rannect.api.dto.AuthTokenPairDto;
import kr.pe.rannect.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static kr.pe.rannect.api.dto.AuthTokenPairDto.*;
import static kr.pe.rannect.api.dto.MemberDto.MemberRequest;
import static kr.pe.rannect.api.dto.MemberDto.SignInRequest;

@RequiredArgsConstructor
@RestController
public class MemberController implements MemberApi {
  private final MemberService memberService;
  @Value("${jwt.accessTokenValidMilliSec}")
  private int accessTokenValidMilliSec; //Refresh Token 유효시간

  @Override
  public void signUp(MemberRequest request) {
    memberService.addNewUser(request);
  }

  @Override
  public ResponseEntity<AuthTokenPairResponse> signIn(SignInRequest request, HttpServletResponse response) {
    AuthTokenPairResponse authTokenPairResponse = memberService.signIn(request);

    //cookie
    Cookie cookie = new Cookie("Access-Token", authTokenPairResponse.getAccessToken());
    cookie.setMaxAge(accessTokenValidMilliSec);
    cookie.setHttpOnly(true); //XSS 공격 방지
    cookie.setPath("/");
    response.addCookie(cookie);

    return new ResponseEntity<>(authTokenPairResponse, HttpStatus.OK);
  }
}
