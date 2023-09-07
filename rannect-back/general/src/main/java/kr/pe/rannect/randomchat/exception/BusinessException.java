/**
 * author         : 우태균
 * description    : 비즈니스 로직 상, 의도적으로 발생시키는 모든 예외 클래스들의 Super Class
 */
package kr.pe.rannect.randomchat.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.toString());
    this.errorCode = errorCode;
  }
}
