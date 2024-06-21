package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    /////////

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;



    // 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
    public void setMember(Member member){
        if(this.member != null){
            member.getMemberMissionList().remove(this);
        }

        this.member = member;

        member.getMemberMissionList().add(this);
    }

    public void setMission(Mission mission){
        if (this.mission != null){
            mission.getMemberMissionList().remove(this);
        }

        this.mission = mission;

        mission.getMemberMissionList().add(this);
    }

}
