package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
// - MemberMission을 추가하는 것이 목적
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    boolean existsByMissionIdAndStatus(Long missionId, MissionStatus status);

}
