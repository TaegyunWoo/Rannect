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

import static kr.pe.rannect.api.dto.MemberDto.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class MemberServiceTest {
  @Mock
  private MemberRepository memberRepository;
  @Mock
  private TokenService tokenService;
  @Mock
  private PasswordEncoder passwordEncoder;
  private MemberService memberService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    memberService = new MemberService(memberRepository, tokenService, passwordEncoder);
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

  @Test
  void 로그인_성공() {
    //GIVEN
    String validAccountId = "myAccountId";
    String validRawPw = "myPassword";
    SignInRequest validRequestDto = SignInRequest.builder()
        .accountId(validAccountId)
        .rawPassword(validRawPw)
        .build();
    String matchedEncodedPw = "myEncodedPassword";
    Member accountIdMatchedMember = Member.builder()
        .id(1L)
        .password(matchedEncodedPw)
        .build();

    //(mock)
    given(memberRepository.findByAccountId(eq(validAccountId))).willReturn(Optional.of(accountIdMatchedMember));
    given(passwordEncoder.matches(eq(validRawPw), eq(matchedEncodedPw))).willReturn(true);

    //WHEN
    try {
      memberService.signIn(validRequestDto);

      //THEN
    } catch (Exception e) {
      fail();
    }
    then(memberRepository).should(times(1)).findByAccountId(anyString());
    then(passwordEncoder).should(times(1)).matches(anyString(), anyString());
    then(tokenService).should(times(1)).issueNewAuthToken(anyLong());
  }

  @Test
  void 존재하지_않는_아이디로_로그인() {
    //GIVEN
    String notExistedAccountId = "not valid account id";
    SignInRequest validRequestDto = SignInRequest.builder()
        .accountId(notExistedAccountId)
        .build();

    //(mock)
    given(memberRepository.findByAccountId(eq(notExistedAccountId))).willReturn(Optional.empty());

    //WHEN
    try {
      memberService.signIn(validRequestDto);

      //THEN
      fail("must throws Exception");
    } catch (InvalidValueException e) {
      assertSame(ErrorCode.SIGN_IN_FAIL, e.getErrorCode());
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  void 올바르지_않은_비밀번호로_로그인() {
    //GIVEN
    String validAccountId = "myAccountId";
    String invalidRawPw = "myInvalidPassword";
    SignInRequest invalidRequestDto = SignInRequest.builder()
        .accountId(validAccountId)
        .rawPassword(invalidRawPw)
        .build();
    String notMatchedEncodedPw = "myEncodedPassword";
    Member accountIdMatchedMember = Member.builder()
        .id(1L)
        .password(notMatchedEncodedPw)
        .build();

    //(mock)
    given(memberRepository.findByAccountId(eq(validAccountId))).willReturn(Optional.of(accountIdMatchedMember));
    given(passwordEncoder.matches(eq(invalidRawPw), eq(notMatchedEncodedPw))).willReturn(false);

    //WHEN
    try {
      memberService.signIn(invalidRequestDto);

      //THEN
      fail("must throws Exception");
    } catch (InvalidValueException e) {
      assertSame(ErrorCode.SIGN_IN_FAIL, e.getErrorCode());
    } catch (Exception e) {
      fail();
    }
    then(memberRepository).should(times(1)).findByAccountId(anyString());
    then(passwordEncoder).should(times(1)).matches(anyString(), anyString());
  }
}