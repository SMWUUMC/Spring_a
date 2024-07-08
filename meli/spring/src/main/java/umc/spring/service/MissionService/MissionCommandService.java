package umc.spring.service.MissionService;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
// - MemberMission을 추가하는 것이 목적
public interface MissionCommandService {

    MemberMission createMemberMission(Long memberId, Long missionId, MissionRequestDTO.MemberMissionDTO request);

}
