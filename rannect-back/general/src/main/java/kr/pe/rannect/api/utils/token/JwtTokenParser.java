/**
 * author         : 우태균
 * description    : 토큰 파싱 유틸리티 컴포넌트
 */
package kr.pe.rannect.api.utils.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import kr.pe.rannect.api.utils.date.LocalDateTimeTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenParser {
  private final String secretKey;
  private final LocalDateTimeTransformer localDateTimeTransformer;

  /**
   * 생성자를 통해, auth-secret.properties 에 정의된 값을 final 필드에 바인딩
   * @param secretKey 비밀키
   * @param localDateTimeTransformer Date->LocalDate 변환 컴포넌트
   */
  public JwtTokenParser(@Value("${jwt.secretKey}") String secretKey, LocalDateTimeTransformer localDateTimeTransformer) {
    this.secretKey = secretKey;
    this.localDateTimeTransformer = localDateTimeTransformer;
  }

  /**
   * 유효한 JWT 토큰 클레임을 파싱하는 메서드
   * 반환된 Optional이 null이라면, 유효하지 않은 토큰
   * @param tokenValue 토큰값
   * @return 토큰이 정상적인 형태라면, 토큰의 클레임들을 Optional로 Wrapping하여 반환
   */
  public Optional<Claims> parseJwtToken(String tokenValue) throws JwtException {
    return Optional.ofNullable(
        Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(tokenValue)
            .getBody());
  }

  /**
   * 토큰 만료기간 추출 메서드
   * @param tokenValue 토큰 값
   * @return 만료기간을 LocalDateTime 으로 반환
   */
  public LocalDateTime extractExpiration(String tokenValue) {
    Date expiration = Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(tokenValue)
        .getBody()
        .getExpiration();
    return localDateTimeTransformer.dateToLocalDateTime(expiration);
  }
}
