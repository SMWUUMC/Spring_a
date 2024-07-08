package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;

// 10주차 4. 진행중인 미션 진행 완료로 바꾸기 API
@Service
@Transactional
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMission completeMemberMission(Long membermissionId){

        MemberMission memberMission = memberMissionRepository.findByIdAndStatus(membermissionId, MissionStatus.CHALLENGING)
                .orElseThrow(() -> new IllegalArgumentException("Challenging membermission not found"));

        memberMission.setStatus(MissionStatus.COMPLETE);

        return memberMissionRepository.save(memberMission);

    }

}
