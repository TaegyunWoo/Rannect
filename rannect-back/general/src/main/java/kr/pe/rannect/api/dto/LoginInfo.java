/**
 * author         : 우태균
 * description    : 로그인 정보
 */
package kr.pe.rannect.api.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Hidden
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginInfo {
  private long memberPk;
}
