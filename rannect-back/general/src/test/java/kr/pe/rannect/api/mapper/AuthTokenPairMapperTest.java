package kr.pe.rannect.api.mapper;

import kr.pe.rannect.api.domain.AuthTokenPair;
import org.junit.jupiter.api.Test;

import static kr.pe.rannect.api.dto.AuthTokenDto.AuthTokenPairResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthTokenPairMapperTest {
  @Test
  void StringToEntity() {
    //GIVEN
    long memberPk = 1L;
    String accessTokenVal = "this is my access token";
    String refreshTokenVal = "this is my refresh token";

    //WHEN
    AuthTokenPair result = AuthTokenPairMapper.INSTANCE.toEntity(memberPk, accessTokenVal, refreshTokenVal);

    //THEN
    assertEquals(memberPk, result.getMemberPk());
    assertEquals(accessTokenVal, result.getAccessToken());
    assertEquals(refreshTokenVal, result.getRefreshToken());
  }

  @Test
  void EntityToResponseDto() {
    //GIVEN
    long memberPk = 1l;
    String accessTokenVal = "this is my access token";
    String refreshTokenVal = "this is my refresh token";
    AuthTokenPair entity = new AuthTokenPair(memberPk, accessTokenVal, refreshTokenVal, "");

    //WHEN
    AuthTokenPairResponse result = AuthTokenPairMapper.INSTANCE.toResponseDto(entity);

    //THEN
    assertEquals(entity.getAccessToken(), result.getAccessToken());
    assertEquals(entity.getRefreshToken(), result.getRefreshToken());
  }
}