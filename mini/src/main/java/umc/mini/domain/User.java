package umc.mini.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.mini.domain.common.BaseEntity;
import umc.mini.domain.enums.Gender;
import umc.mini.domain.enums.UserStatus;
import umc.mini.domain.enums.SocialType;
import umc.mini.domain.mapping.UserAgree;
import umc.mini.domain.mapping.UserMission;
import umc.mini.domain.mapping.UserPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    // 유저 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 이름
    @Column(nullable = false, length = 20)
    private String name;

    // 유저 성별
    @Column(columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // 유저 주소
    @Column(nullable = false, length = 40)
    private String address;

    // 유저 상세 주소
    @Column(nullable = false, length = 40)
    private String specAddress;

    // 유저 생년월일
    private LocalDate birthDate;

    // 유저 이메일
//    @Column(nullable = false, length = 50)
    private String email;

    // 유저 포인트
    @ColumnDefault("0")
    private Integer point;

    // 유저 소셜로그인 타입
    @Column(columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    // 유저 상태
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    // 양방향 연관관계 (1:N)
    // 유저의 동의사항 조건 그륩
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAgree> userAgreeList = new ArrayList<>();

    // 유저의 선호 음식 카테고리 그룹
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPrefer> userPreferList = new ArrayList<>();

    // 유저의 리뷰 그룹
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    // 유저의 미션 그룹
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();
}
