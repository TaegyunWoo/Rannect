/**
 * author         : 우태균
 * description    : 전체 에러 핸들링 ControllerAdvice
 */
package kr.pe.rannect.api.controller.advice;

import kr.pe.rannect.api.dto.ErrorResponseDto;
import kr.pe.rannect.api.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {
  /**
   * RequestBody 에 대해, @Valid에 의해 발생하는 필드 바인딩 오류 처리
   * @param e @Valid에 의해 발생한 예외
   * @return 오류 응답
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error("handleMethodArgumentNotValidException", e);
    ErrorResponseDto dto = ErrorResponseDto.of(e.getBindingResult());
    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

  /**
   * RequestBody 에 대해, 파라미터 바인딩 타입 불일치 오류 처리
   * @param e 관련 예외 객체
   * @return 오류 응답
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    log.error("handleMethodArgumentTypeMismatchException", e);
    ErrorResponseDto dto = ErrorResponseDto.of(e);
    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

  /**
   * 기타 바인딩 오류 처리
   * @param e 관련 예외 객체
   * @return 오류 응답
   */
  @ExceptionHandler(BindException.class)
  protected ResponseEntity<ErrorResponseDto> handleBindException(BindException e) {
    log.error("handleBindException", e);
    ErrorResponseDto dto = ErrorResponseDto.of(e);
    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
  }

  /**
   * 비즈니스 로직 상에서 발생한 모든 예외를 처리
   * @param e 비즈니스 로직 상에서 발생한 예외
   * @return 오류 응답
   */
  @ExceptionHandler(BusinessException.class)
  protected ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException e) {
    log.error("handleBusinessException", e);
    ErrorResponseDto dto = new ErrorResponseDto(e.getErrorCode());
    return new ResponseEntity<>(dto, HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }
}
