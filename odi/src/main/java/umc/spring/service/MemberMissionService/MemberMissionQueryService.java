package umc.spring.service.MemberMissionService;

import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

// 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
public interface MemberMissionQueryService {
    Optional<MemberMission> findMemberMission(Long id);
}
