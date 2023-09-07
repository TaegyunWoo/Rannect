/**
 * author         : 우태균
 * description    : 비즈니스 로직 상, 발생하는 모든 오류코드들을 모은 Enum
 */
package kr.pe.rannect.randomchat.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {

  ;

  private int status; //HTTP 오류 응답 상태 코드
  private String code; //구분 코드
  private String message; //오류 메시지

  ErrorCode(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
