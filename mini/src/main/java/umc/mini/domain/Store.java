package umc.mini.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.mini.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
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

    // 가게 평점 (디폴트 값 0)
    @Column(columnDefinition = "FLOAT")
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

    // 기본값 설정을 위해 @PrePersist 사용
    @PrePersist
    public void prePersist() {
        if (this.score == null) {
            this.score = 0.0f;
        }
    }

    // Region 연관관계 설정
    public void setRegion(Region region) {
        if (this.region != null) {       // 현재 Store가 이미 Region 객체와 연결되어 있는 경우
            this.region.getStoreList().remove(this);
        }
        this.region = region;            // 새로운 연결 설정
        region.getStoreList().add(this); // 새로운 연결 추가
    }
}