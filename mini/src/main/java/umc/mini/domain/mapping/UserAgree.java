package umc.mini.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.mini.domain.Terms;
import umc.mini.domain.User;
import umc.mini.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserAgree extends BaseEntity {

    // 유저 동의사항 조건 그룹 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 단방향 연관관계 (N:1)
    // 유저 id 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 조건 id 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id")
    private Terms terms;

}
