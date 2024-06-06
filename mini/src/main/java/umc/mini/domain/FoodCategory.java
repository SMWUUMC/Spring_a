package umc.mini.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.mini.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {

    // 음식 카테고리 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 음식 카테고리명
    @Column(columnDefinition = "VARCHAR(15)")
    private String name;

    // 음식 카테고리는 보통 수정/삭제를 잘 하지 않으므로 양방향 매핑 X

}