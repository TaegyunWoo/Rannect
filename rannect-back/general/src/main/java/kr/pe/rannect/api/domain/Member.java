/**
 * author         : 우태균
 * description    : 사용자 Entity
 */
package kr.pe.rannect.api.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class Member extends BaseTimeEntity {
  @Id @GeneratedValue
  private Long id;
  @Column(nullable = false, unique = true)
  private String accountId;
  @Column(nullable = false)
  private String password;
  @Column
  private String nickname;
  @Column
  private String interestedIn;

  //양방향 관계
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @Builder.Default
  private List<ChatMember> chatMemberList = new ArrayList<>();

  //편의 메서드
  public void update(String nickname, String interestedIn) {
    this.nickname = nickname;
    this.interestedIn = interestedIn;
  }
}
