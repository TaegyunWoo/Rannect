/**
 * author         : 우태균
 * description    : 사용자 관련 컨트롤러
 */
package kr.pe.rannect.api.controller;

import kr.pe.rannect.api.controller.api.MemberApi;
import kr.pe.rannect.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import static kr.pe.rannect.api.dto.AuthTokenPairDto.AuthTokenPairResponse;
import static kr.pe.rannect.api.dto.MemberDto.MemberRequest;
import static kr.pe.rannect.api.dto.MemberDto.SignInRequest;

@RequiredArgsConstructor
@RestController
public class MemberController implements MemberApi {
  private final MemberService memberService;

  @Override
  public void signUp(MemberRequest request) {
    memberService.addNewUser(request);
  }

  @Override
  public AuthTokenPairResponse signIn(SignInRequest request) {
    return memberService.signIn(request);
  }
}
