/**
 * author         : 우태균
 * description    : 인증 토큰 Mapper
 */
package kr.pe.rannect.api.mapper;

import kr.pe.rannect.api.domain.AuthTokenPair;
import kr.pe.rannect.api.dto.AuthTokenDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AuthTokenPairMapper {
  AuthTokenPairMapper INSTANCE = Mappers.getMapper(AuthTokenPairMapper.class);

  @Mapping(source = "memberPk", target = "memberPk")
  @Mapping(source = "accessToken", target = "accessToken")
  @Mapping(source = "refreshToken", target = "refreshToken")
  @Mapping(constant = "", target = "wsToken")
  AuthTokenPair toEntity(long memberPk, String accessToken, String refreshToken);

  @Mapping(source = "accessToken", target = "accessToken")
  @Mapping(source = "refreshToken", target = "refreshToken")
  AuthTokenDto.AuthTokenPairResponse toResponseDto(AuthTokenPair entity);
}
