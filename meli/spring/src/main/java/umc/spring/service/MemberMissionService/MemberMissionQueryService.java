package umc.spring.service.MemberMissionService;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
// - MemberMission을 추가하는 것이 목적
public interface MemberMissionQueryService {



    // 10주차 4. 진행중인 미션 진행 완료로 바꾸기 API
    Optional<MemberMission> findMemberMission(Long id);



    boolean findChallengingMemberMission(Long id, MissionStatus missionStatus);

}
