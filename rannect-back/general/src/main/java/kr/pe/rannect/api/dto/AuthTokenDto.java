/**
 * author         : 우태균
 * description    : 인증 JWT 토큰 DTO
 */
package kr.pe.rannect.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class AuthTokenDto {
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ExpiredAccessToken {
    private String expiredAccessToken;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ValidRefreshToken {
    @Schema(description = "유효한 Refresh token")
    private String validRefreshToken;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class AuthTokenPairResponse {
    @Schema(description = "Access Token Value")
    private String accessToken;
    @Schema(description = "Refresh Token Value")
    private String refreshToken;
  }
}
