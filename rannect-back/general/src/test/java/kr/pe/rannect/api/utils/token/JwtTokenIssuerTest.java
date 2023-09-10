package kr.pe.rannect.api.utils.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenIssuerTest {
  private JwtTokenIssuer jwtTokenIssuer;

  @Test
  void 사용자_Access_Token_발급() {
    //GIVEN
    String secretKey = "my secret key";
    long accessTokenValidMilliSec = 72000000; // 20시간
    jwtTokenIssuer = new JwtTokenIssuer(secretKey, accessTokenValidMilliSec, -1);
    long memberPk = 1L;

    //WHEN
    String result = jwtTokenIssuer.createMemberToken(memberPk, false);

    //THEN
    Claims body = null;
    try {
      body = Jwts.parser().setSigningKey(secretKey)
          .parseClaimsJws(result)
          .getBody();
    } catch(Exception e) {
      fail();
    }
    Date exp = body.get("exp", Date.class);
    Date now = new Date();
    assertTrue(exp.getTime() - now.getTime() <= accessTokenValidMilliSec);
    assertSame(memberPk, body.get("memberPk", Long.class));
  }

  @Test
  void 사용자_Refresh_Token_발급() {
    //GIVEN
    String secretKey = "my secret key";
    long refreshTokenValidMilliSec = 864285710; // 10일
    jwtTokenIssuer = new JwtTokenIssuer(secretKey, -1, refreshTokenValidMilliSec);
    long memberPk = 1L;

    //WHEN
    String result = jwtTokenIssuer.createMemberToken(memberPk, true);

    //THEN
    Claims body = null;
    try {
      body = Jwts.parser().setSigningKey(secretKey)
          .parseClaimsJws(result)
          .getBody();
    } catch(Exception e) {
      fail();
    }
    Date exp = body.get("exp", Date.class);
    Date now = new Date();
    assertTrue(exp.getTime() - now.getTime() <= refreshTokenValidMilliSec);
    assertSame(memberPk, body.get("memberPk", Long.class));
  }


}