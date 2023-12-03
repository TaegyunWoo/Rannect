package kr.pe.rannect.api.mapper;

import kr.pe.rannect.api.domain.Member;
import kr.pe.rannect.api.dto.MemberDto;
import org.junit.jupiter.api.Test;

import static kr.pe.rannect.api.dto.MemberDto.*;
import static kr.pe.rannect.api.dto.MemberDto.MemberRequest;
import static kr.pe.rannect.api.dto.MemberDto.MemberResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberMapperTest {
  @Test
  void DtoToEntity() {
    //GIVEN
    MemberRequest requestDto = MemberRequest.builder()
        .accountId("my account")
        .nickname("my nickname")
        .interestedIn("my interested in")
        .build();
    String encodedPw = "my encoded pw";

    //WHEN
    Member result = MemberMapper.INSTANCE.toEntity(requestDto, encodedPw);

    //THEN
    assertEquals(requestDto.getAccountId(), result.getAccountId());
    assertEquals(requestDto.getNickname(), result.getNickname());
    assertEquals(requestDto.getInterestedIn(), result.getInterestedIn());
    assertEquals(encodedPw, result.getPassword());
  }

  @Test
  void EntityToResponseDto() {
    //GIVEN
    Member entity = Member.builder()
        .id(1L)
        .accountId("my account id")
        .password("my pw")
        .nickname("my nickname")
        .interestedIn("my interested in")
        .build();

    //WHEN
    MemberResponse result = MemberMapper.INSTANCE.toResponseDto(entity);

    //THEN
    assertEquals(entity.getId(), result.getPk());
    assertEquals(entity.getAccountId(), result.getAccountId());
    assertEquals(entity.getNickname(), result.getNickname());
    assertEquals(entity.getInterestedIn(), result.getInterestedIn());
  }

  @Test
  void EntityToUpdateResponseDto() {
    //GIVEN
    String nickname = "my nickname";
    String interestedIn = "my interested in";
    Member member = Member.builder()
        .nickname(nickname)
        .interestedIn(interestedIn)
        .build();

    //WHEN
    MemberUpdateResponse result = MemberMapper.INSTANCE.toUpdateResponseDto(member);

    //THEN
    assertEquals(nickname, result.getNickname());
    assertEquals(interestedIn, result.getInterestedIn());
  }
}