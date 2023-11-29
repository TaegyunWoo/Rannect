package kr.pe.rannect.api.utils.token;

import com.google.common.base.Supplier;
import io.jsonwebtoken.*;
import kr.pe.rannect.api.utils.date.LocalDateTimeTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Executable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

class JwtTokenParserTest {
  private final String secretKey = "my secret key";
  @Mock
  private LocalDateTimeTransformer localDateTimeTransformer;
  private JwtTokenParser jwtTokenParser;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    jwtTokenParser = new JwtTokenParser(secretKey, localDateTimeTransformer);
  }

  @Test
  void 유효한_JWT_토큰에서_클레임을_파싱() {
    //GIVEN
    Date now = new Date();
    String claimName = "myClaimName";
    String claimVal = "myClaimVal";
    String validTokenVal = Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + 2000000000))
        .claim(claimName, claimVal)
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();

    //WHEN
    Optional<Claims> claims = jwtTokenParser.parseJwtToken(validTokenVal);

    //THEN
    assertTrue(claims.isPresent());
    assertEquals(claimVal, claims.get().get(claimName, String.class));
  }

  @Test
  void 만료된_JWT_토큰에서_클레임을_파싱() {
    //GIVEN
    Date now = new Date();
    String invalidTokenVal = Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() - 2000000000))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();

    //WHEN-THEN
    assertThrows(ExpiredJwtException.class, () -> jwtTokenParser.parseJwtToken(invalidTokenVal));
  }

  @Test
  void 다른_대칭키로_해싱된_JWT_토큰에서_클레임을_파싱() {
    //GIVEN
    Date now = new Date();
    String invalidTokenVal = Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + 2000000))
        .signWith(SignatureAlgorithm.HS512, "different secret key val")
        .compact();

    //WHEN-THEN
    assertThrows(SignatureException.class, () -> jwtTokenParser.parseJwtToken(invalidTokenVal));
  }

  @Test
  void 유효한_JWT_토큰에서_만료기간_추출() {
    //GIVEN
    Date now = new Date();
    Date expireAtDate = new Date(now.getTime() + 2000000000);
    LocalDateTime expireAtLocalDateTime = LocalDateTime.MAX;
    String validTokenVal = Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setIssuedAt(now)
        .setExpiration(expireAtDate)
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();

    //(mock)
    given(localDateTimeTransformer.dateToLocalDateTime(any())).willReturn(expireAtLocalDateTime);

    //WHEN
    LocalDateTime result = jwtTokenParser.extractExpiration(validTokenVal);

    //THEN
    then(localDateTimeTransformer).should(times(1)).dateToLocalDateTime(any());
    assertEquals(expireAtLocalDateTime, result);
  }

  @Test
  void 만료된_JWT_토큰에서_만료기간_추출() {
    //GIVEN
    Date now = new Date();
    Date expireAtDate = new Date(now.getTime() - 2000000000);
    String expiredTokenVal = Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setIssuedAt(now)
        .setExpiration(expireAtDate)
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();

    //WHEN-THEN
    assertThrows(ExpiredJwtException.class, () -> jwtTokenParser.extractExpiration(expiredTokenVal));
  }

  @Test
  void 다른_대칭키로_해싱된_토큰에서_만료기간_추출() {
    //GIVEN
    Date now = new Date();
    Date expireAtDate = new Date(now.getTime() + 2000000000);
    String invalidTokenVal = Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setIssuedAt(now)
        .setExpiration(expireAtDate)
        .signWith(SignatureAlgorithm.HS512, "different secret key val")
        .compact();

    //WHEN-THEN
    assertThrows(SignatureException.class, () -> jwtTokenParser.extractExpiration(invalidTokenVal));
  }
}