/**
 * author         : 우태균
 * description    : Member 관련 매퍼
 */
package kr.pe.rannect.api.mapper;

import kr.pe.rannect.api.domain.Member;
import kr.pe.rannect.api.dto.MemberDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface MemberMapper {
  MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "dto.accountId", target = "accountId")
  @Mapping(source = "dto.nickname", target = "nickname")
  @Mapping(source = "dto.interestedIn", target = "interestedIn")
  @Mapping(source = "encodedPw", target = "password")
  Member toEntity(MemberDto.MemberRequest dto, String encodedPw);

  @Mapping(source = "id", target = "pk")
  @Mapping(source = "accountId", target = "accountId")
  @Mapping(source = "nickname", target = "nickname")
  @Mapping(source = "interestedIn", target = "interestedIn")
  MemberDto.MemberResponse toResponseDto(Member entity);
}
