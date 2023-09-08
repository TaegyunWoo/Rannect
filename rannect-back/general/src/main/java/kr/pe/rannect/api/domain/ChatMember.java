/**
 * author         : 우태균
 * description    : 채팅방에 입장한 사용자 Entity
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
public class ChatMember extends BaseTimeEntity {
  @Id @GeneratedValue
  private Long id;
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Member member;
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private ChatRoom chatRoom;

  //양방향 관계
  @OneToMany(mappedBy = "chatMember", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ChatMessage> chatMessageList = new ArrayList<>();
}
