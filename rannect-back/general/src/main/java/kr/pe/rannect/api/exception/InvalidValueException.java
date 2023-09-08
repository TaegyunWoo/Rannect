/**
 * author         : 우태균
 * description    : 잘못된 값에 의해 발생하는 예외
 */
package kr.pe.rannect.api.exception;

public class InvalidValueException extends BusinessException {

  public InvalidValueException(ErrorCode errorCode) {
    super(errorCode);
  }
}
