package kr.pe.rannect.api.repository;

import kr.pe.rannect.api.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class MemberRepositoryTest {
  @PersistenceContext
  private EntityManager entityManager;
  @Autowired
  private MemberRepository memberRepository;

  @Test
  void 저장() {
    //GIVEN
    Member member = Member.builder()
        .accountId("my account id")
        .password("my pw")
        .nickname("my nickname")
        .interestedIn("my interested in")
        .build();

    //WHEN
    try {
      memberRepository.save(member);
    } catch (Exception e) {
      fail();
    }

    //THEN
    Member persistedEntity = entityManager.find(Member.class, member.getId());
    assertNotNull(persistedEntity);
    assertEquals(member.getAccountId(), persistedEntity.getAccountId());
    assertEquals(member.getPassword(), persistedEntity.getPassword());
    assertEquals(member.getNickname(), persistedEntity.getNickname());
    assertEquals(member.getInterestedIn(), persistedEntity.getInterestedIn());
  }

  @Test
  void 존재하는_계정_아이디로_조회() {
    //GIVEN
    String targetAccountId = "my account id";
    Member member = Member.builder()
        .accountId(targetAccountId)
        .password("my pw")
        .nickname("my nickname")
        .interestedIn("my interested in")
        .build();
    entityManager.persist(member);

    //WHEN
    Optional<Member> result = null;
    try {
      result = memberRepository.findByAccountId(targetAccountId);
    } catch (Exception e) {
      fail();
    }

    //THEN
    assertNotNull(result);
    assertTrue(result.isPresent());
    assertEquals(member.getAccountId(), result.get().getAccountId());
    assertEquals(member.getPassword(), result.get().getPassword());
    assertEquals(member.getNickname(), result.get().getNickname());
    assertEquals(member.getInterestedIn(), result.get().getInterestedIn());
  }

  @Test
  void 존재하지_않는_계정_아이디로_조회() {
    //GIVEN
    String notExistedAccountId = "not existed account id";
    Member member = Member.builder()
        .accountId("my account id")
        .password("my pw")
        .nickname("my nickname")
        .interestedIn("my interested in")
        .build();
    entityManager.persist(member);

    //WHEN
    Optional<Member> result = null;
    try {
      result = memberRepository.findByAccountId(notExistedAccountId);
    } catch (Exception e) {
      fail();
    }

    //THEN
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}