package umc.mini.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.mini.domain.Mission;
import umc.mini.domain.User;
import umc.mini.domain.common.BaseEntity;
import umc.mini.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission extends BaseEntity {

    // 미션 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 미션 상태
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'CHALLENGING'")
    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    // 단방향 연관관계 (N:1)
    // 유저 id 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 미션 id 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void setUser(User user){
        if(this.user != null){
            user.getUserMissionList().remove(this);
        }

        this.user = user;
        user.getUserMissionList().add(this);
    }

    public void setMission(Mission mission){
        if (this.mission != null){
            mission.getUserMissionList().remove(this);
        }

        this.mission = mission;
        mission.getUserMissionList().add(this);
    }
}
