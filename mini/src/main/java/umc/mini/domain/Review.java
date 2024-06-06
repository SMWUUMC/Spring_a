package umc.mini.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.mini.domain.common.BaseEntity;
import umc.mini.domain.mapping.UserMission;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    // 리뷰 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 리뷰 내용
    @Column(columnDefinition = "TEXT")
    private String body;

    // 리뷰 평점
    @Column(columnDefinition = "FLOAT", nullable = false)
    private Float score;

    // 단방향 연관관계 (N:1)
    // 유저 id 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 가게 id 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // 양방향 연관관계 매핑
    // 리뷰 이미지 그룹
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImageList = new ArrayList<>();
}