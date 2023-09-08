/**
 * author         : 우태균
 * description    : 채팅메시지 Entity
 */
package kr.pe.rannect.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class ChatMessage extends BaseTimeEntity {
  @Id @GeneratedValue
  private Long id;
  @Column(length = 510, nullable = false)
  private String message;
  @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private ChatMember chatMember;
}
