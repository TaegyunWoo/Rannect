/**
 * author         : 우태균
 * description    : 토큰 관련 Service
 */
package kr.pe.rannect.api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import kr.pe.rannect.api.domain.AuthTokenPair;
import kr.pe.rannect.api.exception.AuthenticationException;
import kr.pe.rannect.api.exception.ErrorCode;
import kr.pe.rannect.api.mapper.AuthTokenPairMapper;
import kr.pe.rannect.api.repository.AuthTokenPairRepository;
import kr.pe.rannect.api.utils.token.JwtTokenIssuer;
import kr.pe.rannect.api.utils.token.JwtTokenParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static kr.pe.rannect.api.dto.AuthTokenDto.AuthTokenPairResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenService {
  private final AuthTokenPairRepository authTokenPairRepository;
  private final JwtTokenIssuer jwtTokenIssuer;
  private final JwtTokenParser jwtTokenParser;

  @Transactional
  public AuthTokenPairResponse issueNewAuthToken(long memberPk) {
    String accessTokenVal = jwtTokenIssuer.createMemberToken(memberPk, false);
    String refreshTokenVal = jwtTokenIssuer.createMemberToken(memberPk, true);
    AuthTokenPair authTokenPair = AuthTokenPairMapper.INSTANCE.toEntity(memberPk, accessTokenVal, refreshTokenVal);

    //인증 토큰쌍 저장
    authTokenPairRepository.save(authTokenPair);

    AuthTokenPairResponse response = AuthTokenPairMapper.INSTANCE.toResponseDto(authTokenPair);

    log.info("[Service] New token pair(memberPk: {}) issued", memberPk);

    return response;
  }

  @Transactional(noRollbackFor = {AuthenticationException.class})
  public AuthTokenPairResponse reissueAuthToken(String expiredAccessToken, String validRefreshToken, String requestedIpAddr) {
    //1. Refresh 토큰 유효성 검증
    Claims claims = null;
    try {
      claims = jwtTokenParser.parseJwtToken(validRefreshToken).orElseThrow(() -> {
        log.info("[Service] User({}) tried to access with access token without any claims.", requestedIpAddr);
        return new AuthenticationException(ErrorCode.BAD_TOKEN);
      });
    } catch (ExpiredJwtException ex) {
      log.info("[Service] User({}) tried to access with expired refresh token.", requestedIpAddr);
      throw new AuthenticationException(ErrorCode.EXPIRED_TOKEN);
    } catch (JwtException ex) {
      log.info("[Service] User({}) tried to access with bad refresh token.", requestedIpAddr);
      throw new AuthenticationException(ErrorCode.BAD_TOKEN);
    }
    long memberPk = claims.get("memberPk", Long.class);

    //2. 실제로 만료된 Access Token인지 확인
    boolean isExpiredAccessToken = false;
    try {
      jwtTokenParser.parseJwtToken(expiredAccessToken);
    } catch (ExpiredJwtException ex) {
      isExpiredAccessToken = true;
      //ignored
    }
    if (!isExpiredAccessToken) { //실제로 만료되지 않았다면
      log.info("[Service] User({}) tried to access with not expired token.", requestedIpAddr);
      authTokenPairRepository.deleteById(memberPk); //Redis 에서 제거 (강제 토큰 비활성화 처리)
      throw new AuthenticationException(ErrorCode.NOT_EXPIRED_ACCESS_TOKEN);
    }

    //3. 실제로 저장된 토큰쌍인지 확인
    AuthTokenPair savedAuthTokenPair = authTokenPairRepository.findById(memberPk).orElseThrow(() -> {
      log.info("[Service] User({})'s access token claim is bad.", requestedIpAddr);
      return new AuthenticationException(ErrorCode.BAD_TOKEN);
    });
    if (!savedAuthTokenPair.getAccessToken().equals(expiredAccessToken)
        || !savedAuthTokenPair.getRefreshToken().equals(validRefreshToken)) {
      log.info("[Service] User({})'s token pair is not exist.", requestedIpAddr);
      throw new AuthenticationException(ErrorCode.BAD_TOKEN);
    }

    //새 토큰 발급
    return issueNewAuthToken(memberPk);
  }


}
