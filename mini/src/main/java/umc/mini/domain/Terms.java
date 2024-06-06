package umc.mini.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.mini.domain.common.BaseEntity;
import umc.mini.domain.mapping.UserAgree;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {

    // 동의사항 조건 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 조건 제목
    @Column(columnDefinition = "VARCHAR(20)")
    private String title;

    // 조건 내용
    @Column(columnDefinition = "TEXT")
    private String body;

    // 조건 선택
    @Column(columnDefinition = "BOOLEAN")
    private Boolean optional;

    // 양방향 연관관계 (1:N)
    // 유저 동의사항 매핑
    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
    private List<UserAgree> userAgreeList = new ArrayList<>();
}