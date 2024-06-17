package umc.spring.service.MemberMissionService;

import umc.spring.domain.enums.MissionStatus;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
// - MemberMission을 추가하는 것이 목적
public interface MemberMissionQueryService {

    boolean findMemberMission(Long id, MissionStatus missionStatus);

}
