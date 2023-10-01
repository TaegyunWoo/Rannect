/**
 * author         : 우태균
 * description    : 사용자 Repository
 */
package kr.pe.rannect.api.repository;

import kr.pe.rannect.api.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
  Optional<Member> findByAccountId(String accountId);
}
