/**
 * author         : 우태균
 * description    : 사용자 Service
 */
package kr.pe.rannect.api.service;

import kr.pe.rannect.api.domain.Member;
import kr.pe.rannect.api.exception.ErrorCode;
import kr.pe.rannect.api.exception.InvalidValueException;
import kr.pe.rannect.api.mapper.MemberMapper;
import kr.pe.rannect.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.pe.rannect.api.dto.AuthTokenDto.AuthTokenPairResponse;
import static kr.pe.rannect.api.dto.MemberDto.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
  private final MemberRepository memberRepository;
  private final TokenService tokenService;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public MemberResponse addNewUser(MemberRequest request) {
    //Account ID 중복 확인
    String requestedAccountId = request.getAccountId();
    memberRepository.findByAccountId(requestedAccountId).ifPresent(member -> {
          throw new InvalidValueException(ErrorCode.DUPLICATED_ACCOUNT_ID);
    });

    //비밀번호 암호화
    String rawPassword = request.getRawPassword();
    String encodedPassword = passwordEncoder.encode(rawPassword);

    //사용자 저장
    Member member = MemberMapper.INSTANCE.toEntity(request, encodedPassword);
    memberRepository.save(member);

    MemberResponse response = MemberMapper.INSTANCE.toResponseDto(member);

    log.info("[Service] New user(account id: {}) signed up.", member.getAccountId());

    return response;
  }

  @Transactional(readOnly = true)
  public SignInResponse signIn(SignInRequest request) {
    //Account ID 확인
    String requestedAccountId = request.getAccountId();
    Member member = memberRepository.findByAccountId(requestedAccountId).orElseThrow(
        () -> new InvalidValueException(ErrorCode.SIGN_IN_FAIL)
    );

    //PW 확인
    String requestedRawPw = request.getRawPassword();
    boolean isCorrectPw = passwordEncoder.matches(requestedRawPw, member.getPassword());
    if (!isCorrectPw) throw new InvalidValueException(ErrorCode.SIGN_IN_FAIL);

    //토큰쌍 발급
    AuthTokenPairResponse tokenPairResponse = tokenService.issueNewAuthToken(member.getId());

    log.info("[Service] User(memberPk: {}) signed in.", member.getId());

    return MemberMapper.INSTANCE.toSignInResponseDto(member, tokenPairResponse);
  }

  @Transactional
  public MemberUpdateResponse updateMember(long targetMemberPk, MemberUpdateRequest request) {
    Member targetMember = memberRepository.findById(targetMemberPk)
        .orElseThrow(() -> new InvalidValueException(ErrorCode.NOT_FOUND_DATA));
    targetMember.update(request.getNickname(), request.getInterestedIn());

    log.info("[Service] User(memberPk: {}) updated.", targetMemberPk);

    return MemberMapper.INSTANCE.toUpdateResponseDto(targetMember);
  }

  @Transactional(readOnly = true)
  public MemberResponse inquiryMember(long memberPk) {
    Member member = memberRepository.findById(memberPk).orElseThrow(
        () -> new InvalidValueException(ErrorCode.NOT_FOUND_DATA)
    );
    return MemberMapper.INSTANCE.toResponseDto(member);
  }
}
