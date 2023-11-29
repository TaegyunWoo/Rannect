/**
 * author         : 우태균
 * description    : 토큰 관련 Service
 */
package kr.pe.rannect.api.service;

import kr.pe.rannect.api.domain.AuthTokenPair;
import kr.pe.rannect.api.mapper.AuthTokenPairMapper;
import kr.pe.rannect.api.repository.AuthTokenPairRepository;
import kr.pe.rannect.api.utils.token.JwtTokenIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.pe.rannect.api.dto.AuthTokenPairDto.AuthTokenPairResponse;

@RequiredArgsConstructor
@Service
public class TokenService {
  private final AuthTokenPairRepository authTokenPairRepository;
  private final JwtTokenIssuer tokenIssuer;

  @Transactional
  public AuthTokenPairResponse issueNewAuthToken(long memberPk) {
    String accessTokenVal = tokenIssuer.createMemberToken(memberPk, false);
    String refreshTokenVal = tokenIssuer.createMemberToken(memberPk, true);
    AuthTokenPair authTokenPair = AuthTokenPairMapper.INSTANCE.toEntity(memberPk, accessTokenVal, refreshTokenVal);

    //인증 토큰쌍 저장
    authTokenPairRepository.save(authTokenPair);

    AuthTokenPairResponse response = AuthTokenPairMapper.INSTANCE.toResponseDto(authTokenPair);
    return response;
  }
}
