package kr.pe.rannect.api.service;

import kr.pe.rannect.api.domain.Member;
import kr.pe.rannect.api.exception.ErrorCode;
import kr.pe.rannect.api.exception.InvalidValueException;
import kr.pe.rannect.api.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static kr.pe.rannect.api.dto.MemberDto.MemberRequest;
import static kr.pe.rannect.api.dto.MemberDto.MemberResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class MemberServiceTest {
  @Mock
  private MemberRepository memberRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  private MemberService memberService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    memberService = new MemberService(memberRepository, passwordEncoder);
  }

  @Test
  void 중복되지_않는_Account_ID로_새_사용자_추가() {
    //GIVEN
    MemberRequest requestDto = MemberRequest.builder()
        .accountId("my account")
        .nickname("my nickname")
        .interestedIn("my interested in")
        .build();

    //(mock)
    given(memberRepository.findByAccountId(requestDto.getAccountId())).willReturn(Optional.empty());

    //WHEN
    MemberResponse result = null;
    try {
      result = memberService.addNewUser(requestDto);
    } catch (Exception e) {
      fail();
    }

    //THEN
    assertEquals(requestDto.getAccountId(), result.getAccountId());
    assertEquals(requestDto.getNickname(), result.getNickname());
    assertEquals(requestDto.getInterestedIn(), result.getInterestedIn());
    then(memberRepository).should(times(1)).findByAccountId(requestDto.getAccountId());
  }

  @Test
  void 중복되는_Account_ID로_새_사용자_추가() {
    //GIVEN
    String duplicatedAccountId = "duplicated account id";
    MemberRequest requestDto = MemberRequest.builder()
        .accountId(duplicatedAccountId)
        .nickname("my nickname")
        .interestedIn("my interested in")
        .build();

    //(mock)
    given(memberRepository.findByAccountId(requestDto.getAccountId())).willReturn(Optional.of(new Member()));

    //WHEN
    try {
      memberService.addNewUser(requestDto);

      //THEN
      fail("must throws Exception");
    } catch (InvalidValueException e) {
      assertSame(ErrorCode.DUPLICATED_ACCOUNT_ID, e.getErrorCode());
    } catch (Exception e) {
      fail();
    }
  }
}