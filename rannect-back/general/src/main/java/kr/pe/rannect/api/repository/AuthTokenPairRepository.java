/**
 * author         : 우태균
 * description    : 인증 토큰 Repository
 */
package kr.pe.rannect.api.repository;

import kr.pe.rannect.api.domain.AuthTokenPair;
import org.springframework.data.repository.CrudRepository;

public interface AuthTokenPairRepository extends CrudRepository<AuthTokenPair, String> {
}
