/**
 * author         : 우태균
 * description    : Member 관련 매퍼
 */
package kr.pe.rannect.api.mapper;

import kr.pe.rannect.api.domain.AuthTokenPair;
import kr.pe.rannect.api.domain.Member;
import kr.pe.rannect.api.dto.AuthTokenDto;
import kr.pe.rannect.api.dto.MemberDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
    builder = @Builder(disableBuilder = true)
)
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

  @Mapping(source = "nickname", target = "nickname")
  @Mapping(source = "interestedIn", target = "interestedIn")
  MemberDto.MemberUpdateResponse toUpdateResponseDto(Member entity);

  @Mapping(source = "authTokenPairResponse", target = "tokenInfo")
  @Mapping(source = "member", target = "memberInfo")
  MemberDto.SignInResponse toSignInResponseDto(Member member, AuthTokenDto.AuthTokenPairResponse authTokenPairResponse);
}
