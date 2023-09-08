/**
 * author         : 우태균
 * description    : 채팅방 Enitty
 */
package kr.pe.rannect.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class ChatRoom extends BaseTimeEntity {
  @Id @GeneratedValue
  private Long id;
  @Column(nullable = false)
  private String type;
  @Column
  private String topic;

  //양방향 관계
  @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ChatMember> chatMemberList = new ArrayList<>();
}
