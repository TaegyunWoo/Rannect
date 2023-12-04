/**
 * author         : 우태균
 * description    : 사용자 관련 컨트롤러
 */
package kr.pe.rannect.api.controller;

import kr.pe.rannect.api.controller.api.MemberApi;
import kr.pe.rannect.api.dto.LoginInfo;
import kr.pe.rannect.api.service.MemberService;
import kr.pe.rannect.api.service.TokenService;
import kr.pe.rannect.api.utils.http.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static kr.pe.rannect.api.dto.AuthTokenDto.AuthTokenPairResponse;
import static kr.pe.rannect.api.dto.MemberDto.*;
import static kr.pe.rannect.api.dto.MemberDto.MemberRequest;
import static kr.pe.rannect.api.dto.MemberDto.SignInRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController implements MemberApi {
  private final MemberService memberService;
  private final TokenService tokenService;
  private final CookieUtils cookieUtils;

  @Override
  public void signUp(MemberRequest request) {
    memberService.addNewUser(request);
  }

  @Override
  public ResponseEntity<AuthTokenPairResponse> signIn(SignInRequest request, HttpServletResponse response) {
    AuthTokenPairResponse authTokenPairResponse = memberService.signIn(request);

    //Access Token 쿠키 설정
    cookieUtils.addAuthCookie(response, authTokenPairResponse.getAccessToken());

    return new ResponseEntity<>(authTokenPairResponse, HttpStatus.OK);
  }

  @Override
  public void signOut(LoginInfo loginInfo, HttpServletResponse response) {
    tokenService.deleteTokenPair(loginInfo.getMemberPk());

    //Access Token 쿠키 제거
    cookieUtils.removeAuthCookie(response);

    log.info("[Controller] User(memberPk: {}) is signed out successfully.", loginInfo.getMemberPk());
  }

  @Override
  public MemberUpdateResponse updateMember(LoginInfo loginInfo, MemberUpdateRequest request) {
    return memberService.updateMember(loginInfo.getMemberPk(), request);
  }

  @Override
  public MemberResponse getMyMemberInfo(LoginInfo loginInfo) {
    return memberService.inquiryMember(loginInfo.getMemberPk());
  }
}
