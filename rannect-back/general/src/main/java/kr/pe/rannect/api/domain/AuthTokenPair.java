/**
 * author         : 우태균
 * description    : 인증 토큰 쌍
 */
package kr.pe.rannect.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@AllArgsConstructor
@RedisHash("TokenPair") //key prefix
public class AuthTokenPair {
  @Id
  private Long memberPk;
  private String accessToken;
  private String refreshToken;
  @Builder.Default
  private String wsToken = "";
}
