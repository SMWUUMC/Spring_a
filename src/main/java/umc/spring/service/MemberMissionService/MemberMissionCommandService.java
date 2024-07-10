package umc.spring.service.MemberMissionService;

import umc.spring.domain.mapping.MemberMission;

// 10주차 4. 진행중인 미션 진행 완료로 바꾸기 API
public interface MemberMissionCommandService {

    MemberMission completeMemberMission(Long membermissionId);

}
