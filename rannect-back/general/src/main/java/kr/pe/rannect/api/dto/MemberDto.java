/**
 * author         : 우태균
 * description    : 사용자 DTO
 */
package kr.pe.rannect.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MemberDto {
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class MemberRequest {
    @Schema(description = "4자 이상, 12자 이하, 영문 및 숫자만 가능", example = "myAccountId")
    @NotNull
    @Length(min = 4, max = 12)
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "계정ID는 영문자 및 숫자만 가능")
    private String accountId;
    @Schema(description = "6자 이상, 15자 이하, 영문 및 숫자 가능", example = "myPassword")
    @NotNull
    @Length(min = 6, max = 15)
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "비밀번호는 영어,숫자만 가능")
    private String rawPassword;
    @Schema(description = "2자 이상, 15자 이하, 영문·한글·숫자 가능", example = "myNickname")
    @NotNull
    @Length(min = 2, max = 15)
    @Pattern(regexp = "[a-zA-Z0-9가-힣]*")
    private String nickname;
    @Schema(description = "50자 이하", example = "축구에 관심이 많아요~!")
    @Length(max = 50)
    private String interestedIn;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class MemberUpdateRequest {
    @Schema(description = "2자 이상, 15자 이하, 영문·한글·숫자 가능", example = "myNickname")
    @NotNull
    @Length(min = 2, max = 15)
    @Pattern(regexp = "[a-zA-Z0-9가-힣]*")
    private String nickname;
    @Schema(description = "50자 이하", example = "축구에 관심이 많아요~!")
    @Length(max = 50)
    private String interestedIn;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class MemberUpdateResponse {
    @Schema(description = "닉네임", example = "myNickname")
    private String nickname;
    @Schema(description = "관심있는 주제", example = "축구에 관심이 많아요~!")
    private String interestedIn;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class MemberResponse {
    @Schema()
    private long pk;
    @Schema(description = "계정ID", example = "myAccountId")
    private String accountId;
    @Schema(description = "닉네임", example = "myNickname")
    private String nickname;
    @Schema(description = "관심있는 주제", example = "축구에 관심이 많아요~!")
    private String interestedIn;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class SignInRequest {
    @Schema(description = "로그인 아이디", example = "myAccountId")
    @NotBlank
    private String accountId;
    @Schema(description = "로그인 비밀번호", example = "myPassword")
    @NotBlank
    private String rawPassword;
  }
}
