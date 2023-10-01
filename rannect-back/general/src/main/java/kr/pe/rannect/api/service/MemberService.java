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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.pe.rannect.api.dto.MemberDto.MemberRequest;
import static kr.pe.rannect.api.dto.MemberDto.MemberResponse;

@RequiredArgsConstructor
@Service
public class MemberService {
  private final MemberRepository memberRepository;
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
    return response;
  }

}