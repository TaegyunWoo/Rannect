/**
 * author         : 우태균
 * description    : 인가 관련 예외
 */
package kr.pe.rannect.api.exception;

public class AuthenticationException extends BusinessException {
  public AuthenticationException(ErrorCode errorCode) {
    super(errorCode);
  }
}
