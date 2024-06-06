package umc.mini.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.mini.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    // 가게 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 가게 이름
    @Column(nullable = false, length = 50)
    private String name;

    // 가게 주소
    @Column(nullable = false, length = 50)
    private String address;

    // 가게 평점
    @Column(nullable = false, columnDefinition = "FLOAT")
    private Float score;

    // 단방향 연관관계
    // 지역 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    // 양방향 연관관계
    // 가게의 리뷰 그룹
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> storeReviewList = new ArrayList<>();
}