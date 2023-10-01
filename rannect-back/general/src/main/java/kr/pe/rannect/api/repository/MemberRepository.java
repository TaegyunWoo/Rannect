/**
 * author         : 우태균
 * description    : 사용자 Repository
 */
package kr.pe.rannect.api.repository;

import kr.pe.rannect.api.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class MemberRepository {
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * 사용자 엔티티 저장
   * @param member 저장할 엔티티
   */
  public void save(Member member) {
    entityManager.persist(member);
  }

  /**
   * 계정 ID로 사용자 조회
   * @param accountId 조회할 계정 ID
   * @return 조회 결과 (결과가 없다면, Optional.empty())
   */
  public Optional<Member> findByAccountId(String accountId) {
    String jpql = "select m from Member m" +
        " where m.accountId = :accountId";
    TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class)
        .setParameter("accountId", accountId);

    try {
      Member result = query.getSingleResult();
      return Optional.of(result);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
