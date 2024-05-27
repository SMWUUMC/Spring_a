package umc.jupy.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.jupy.domain.common.BaseEntity;
import umc.jupy.domain.enums.Gender;
import umc.jupy.domain.enums.MemberStatus;
import umc.jupy.domain.enums.SocialType;
import umc.jupy.domain.mapping.MemberAgree;
import umc.jupy.domain.mapping.MemberMission;
import umc.jupy.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String specAddress;

    private LocalDate inactiveDate;

    private String email;

    private Integer point;

    private LocalDate birth;
    // 추후 LocalDate birthDate = LocalDate.of(2001, 8, 1);과 같이 저장하면 됨

    private Integer phoneNumber; // null 허용 설정 추가해야 함

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}