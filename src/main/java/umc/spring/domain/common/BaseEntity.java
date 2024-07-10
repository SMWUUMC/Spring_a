package umc.spring.domain.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
// 생성 시간(createdAt), 수정 시간(updatedAt)같은
// 공통 맵핑 정보가 필요할 때 사용한다
// 부모 클래스(BaseEntity)에서 선언하고 속성만 상속 받아서 사용하고 싶을 때 사용한다
// BaseEntity를 상속받는 클래스는 모두 createdAt, updatedAt 필드가 있어야 한다
@EntityListeners(AuditingEntityListener.class)
// JPA Entity에서 이벤트가 발생할 관련 코드를 실행한다
@Getter
public abstract class BaseEntity {
    // created_at, updated_at은 모든 엔티티에서 다 사용하므로
    // 매번 넣어주기가 매우 귀찮다
    // abstract class로 만들어서 모든 엔티티 클래스마다
    // BaseEntity를 상속해준다

    @CreatedDate
    // 생성된 시간 정보
    // 생성일자를 관리하는 필드에 현재 날짜를 주입하는 작업을 수행한다
    private LocalDateTime createdAt;

    @LastModifiedDate
    // 수정된 시간 정보
    private LocalDateTime updatedAt;

}
