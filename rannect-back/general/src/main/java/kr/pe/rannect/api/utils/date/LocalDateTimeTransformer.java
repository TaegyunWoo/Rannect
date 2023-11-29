/**
 * author         : 우태균
 * description    : LocalDate(Time) <-> Date(Time) 변환 유틸리티 컴포넌트
 */
package kr.pe.rannect.api.utils.date;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class LocalDateTimeTransformer {
  /**
   * Date -> LocalDateTime
   * @param date from
   * @return to
   */
  public LocalDateTime dateToLocalDateTime(Date date) {
    LocalDateTime localDateTime = date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    return localDateTime;
  }

  /**
   * Date -> LocalDate
   * @param date from
   * @return to
   */
  public LocalDate dateToLocalDate(Date date) {
    LocalDate localDate = date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
    return localDate;
  }

  /**
   * LocalDateTime -> Date
   * @param localDateTime from
   * @return to
   */
  public Date localDateTimeToDate(LocalDateTime localDateTime) {
    Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    Date date = Date.from(instant);
    return date;
  }

  /**
   * LocalDate -> Date
   * @param localDate from
   * @return to
   */
  public Date localDateToDate(LocalDate localDate) {
    Date date = java.sql.Date.valueOf(localDate);
    return date;
  }
}
