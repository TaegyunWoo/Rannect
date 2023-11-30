/**
 * author         : 우태균
 * description    : 인가 처리 인터셉터
 */
package kr.pe.rannect.api.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import kr.pe.rannect.api.exception.AuthenticationException;
import kr.pe.rannect.api.exception.ErrorCode;
import kr.pe.rannect.api.repository.AuthTokenPairRepository;
import kr.pe.rannect.api.utils.token.JwtTokenParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {
  private final AuthTokenPairRepository authTokenPairRepository;
  private final JwtTokenParser jwtTokenParser;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    final String requestedIpAddr = request.getRemoteAddr();

    if (request.getCookies() == null) {
      log.info("[Auth] User({}) requested with no access token.", requestedIpAddr);
      throw new AuthenticationException(ErrorCode.CANNOT_FIND_ACCESS_TOKEN);
    }

    //1. Cookie 헤더에서 Access Token 추출
    String reqAccessToken = Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals("Access-Token"))
        .map(Cookie::getValue)
        .findFirst()
        .orElseThrow(() -> {
          log.info("[Auth] User({}) requested with no access token", requestedIpAddr);
          return new AuthenticationException(ErrorCode.CANNOT_FIND_ACCESS_TOKEN);
        });
    reqAccessToken = URLDecoder.decode(reqAccessToken, StandardCharsets.UTF_8);

    //2. Bearer로 시작하는지 확인
    if (!reqAccessToken.startsWith("Bearer ")) {
      log.info("[Auth] User({}) sent access token with no 'Bearer' in request header.", requestedIpAddr);
      throw new AuthenticationException(ErrorCode.NO_BEARER);
    }
    reqAccessToken = reqAccessToken.substring("Bearer ".length());

    //3. 토큰 유효성 검증
    Claims claims = null;
    try {
      claims = jwtTokenParser.parseJwtToken(reqAccessToken).orElseThrow(() -> {
        log.info("[Auth] User({}) tried to access with access token without any claims.", requestedIpAddr);
        return new AuthenticationException(ErrorCode.BAD_TOKEN);
      });
    } catch (ExpiredJwtException ex) {
      log.info("[Auth] User({}) tried to access with expired access token.", requestedIpAddr);
      throw new AuthenticationException(ErrorCode.EXPIRED_TOKEN);
    } catch (JwtException ex) {
      log.info("[Auth] User({}) tried to access with bad access token.", requestedIpAddr);
      throw new AuthenticationException(ErrorCode.BAD_TOKEN);
    }

    //4. Redis에 저장된 토큰인지 확인
    long memberPk = claims.get("memberPk", Long.class);
    authTokenPairRepository.findById(memberPk)
        .orElseThrow(() -> {
          log.info("[Auth] User({}) tried to access without saved access token.", requestedIpAddr);
          return new AuthenticationException(ErrorCode.BAD_TOKEN);
        });

    //5. 인가받은 사용자 정보를 Argument Resolver에게 넘겨주기 위해, attribute 추가
    request.setAttribute("memberPk", memberPk);

    log.info("[Auth] User({}) is authorized.", requestedIpAddr);
    return true;
  }
}
