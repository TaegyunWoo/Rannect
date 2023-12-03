/**
 * author         : 우태균
 * description    : 인가 관련 API
 */
package kr.pe.rannect.api.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.pe.rannect.api.dto.AuthTokenDto;
import kr.pe.rannect.api.dto.ErrorResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "인가 관련 API")
@RequestMapping("/auth")
public interface AuthApi {
  @Operation(summary = "Token 재발급")
  @ApiResponse(responseCode = "200", description = "재발급 성공")
  @ApiResponse(responseCode = "401", description = "토큰 관련 오류", content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))})
  @PostMapping("/reissue-tokens")
  AuthTokenDto.AuthTokenPairResponse reissueAccessToken(
      @RequestBody AuthTokenDto.ValidRefreshToken validRefreshToken,
      HttpServletRequest httpServletRequest
  );
}
