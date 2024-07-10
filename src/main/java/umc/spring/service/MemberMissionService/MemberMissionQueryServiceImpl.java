package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;

import java.util.Optional;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
// - MemberMission을 추가하는 것이 목적
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;



    // 10주차 4. 진행중인 미션 진행 완료로 바꾸기 API
    public Optional<MemberMission> findMemberMission(Long id){
        return memberMissionRepository.findById(id);
    }



    @Override
    public boolean findChallengingMemberMission(Long id, MissionStatus missionStatus){
        return memberMissionRepository.existsByMissionIdAndStatus(id, MissionStatus.CHALLENGING);
    }

}
