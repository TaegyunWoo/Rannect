/**
 * author         : 우태균
 * description    : 엔티티 생성 및 수정 시각을 공통으로 관리하는 슈퍼 클래스
 */
package kr.pe.rannect.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class BaseTimeEntity {
  @CreatedDate
  private LocalDateTime createdDatetime;
  @LastModifiedDate
  private LocalDateTime modifiedDatetime;
}
