/**
 * author         : 우태균
 * description    : 인증 토큰 Mapper
 */
package kr.pe.rannect.api.mapper;

import kr.pe.rannect.api.domain.AuthTokenPair;
import kr.pe.rannect.api.dto.AuthTokenPairDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AuthTokenPairMapper {
  AuthTokenPairMapper INSTANCE = Mappers.getMapper(AuthTokenPairMapper.class);

  @Mapping(source = "accessToken", target = "accessToken")
  @Mapping(source = "refreshToken", target = "refreshToken")
  AuthTokenPair toEntity(String accessToken, String refreshToken);

  @Mapping(source = "accessToken", target = "accessToken")
  @Mapping(source = "refreshToken", target = "refreshToken")
  AuthTokenPairDto.AuthTokenPairResponse toResponseDto(AuthTokenPair entity);
}
