package kr.pe.rannect.api.utils.date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDateTimeTransformerTest {
  private LocalDateTimeTransformer localDateTimeTransformer;

  @BeforeEach
  void setUp() {
    localDateTimeTransformer = new LocalDateTimeTransformer();
  }

  @Test
  void Date를_LocalDateTime으로_변환() {
    //GIVEN
    int year = 2023;
    int month = 1;
    int day = 1;
    Date date = new Date(year - 1900, month - 1, day);

    //WHEN
    LocalDateTime result = localDateTimeTransformer.dateToLocalDateTime(date);

    //THEN
    assertEquals(year, result.getYear());
    assertEquals(month, result.getMonthValue());
    assertEquals(day, result.getDayOfMonth());
    assertEquals(0, result.getHour());
    assertEquals(0, result.getMinute());
    assertEquals(0, result.getSecond());
  }

  @Test
  void Date를_LocalDate으로_변환() {
    //GIVEN
    int year = 2023;
    int month = 1;
    int day = 1;
    Date date = new Date(year - 1900, month - 1, day);

    //WHEN
    LocalDate result = localDateTimeTransformer.dateToLocalDate(date);

    //THEN
    assertEquals(year, result.getYear());
    assertEquals(month, result.getMonthValue());
    assertEquals(day, result.getDayOfMonth());
  }

  @Test
  void LocalDateTime을_Date으로_변환() {
    //GIVEN
    int year = 2023;
    int month = 1;
    int day = 1;
    int hour = 1;
    int minute = 1;
    int second = 1;
    LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);

    //WHEN
    Date result = localDateTimeTransformer.localDateTimeToDate(localDateTime);

    //THEN
    assertEquals(year, result.getYear() + 1900);
    assertEquals(month, result.getMonth() + 1);
    assertEquals(day, result.getDay() + 1);
  }

  @Test
  void LocalDate을_Date으로_변환() {
    //GIVEN
    int year = 2023;
    int month = 1;
    int day = 1;
    LocalDate localDate = LocalDate.of(year, month, day);

    //WHEN
    Date result = localDateTimeTransformer.localDateToDate(localDate);

    //THEN
    assertEquals(year, result.getYear() + 1900);
    assertEquals(month, result.getMonth() + 1);
    assertEquals(day, result.getDay() + 1);
  }
}