/**
 * author         : 우태균
 * description    : 사용자 관련 Api Interface
 */
package kr.pe.rannect.api.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.pe.rannect.api.dto.AuthTokenPairDto;
import kr.pe.rannect.api.dto.ErrorResponseDto;
import kr.pe.rannect.api.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "사용자 관련 API")
@RequestMapping("/members")
public interface MemberApi {
  @Operation(summary = "회원가입")
  @ApiResponse(responseCode = "200", description = "회원가입 성공")
  @ApiResponse(responseCode = "400", description = "입력 형식 오류, 조건사항 비만족, 아이디 중복", content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))})
  @PostMapping
  void signUp(
      @RequestBody @Valid MemberDto.MemberRequest request
  );

  @Operation(summary = "로그인")
  @ApiResponse(responseCode = "200", description = "회원가입 성공")
  @ApiResponse(responseCode = "400", description = "입력 형식 오류, 조건사항 비만족, 아이디 중복", content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))})
  @PostMapping("/sign-in")
  AuthTokenPairDto.AuthTokenPairResponse signIn(
      @RequestBody @Valid MemberDto.SignInRequest request
  );
}
