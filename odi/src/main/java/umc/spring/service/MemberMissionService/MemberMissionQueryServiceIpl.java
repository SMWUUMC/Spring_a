package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
// 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
public class MemberMissionQueryServiceIpl implements MemberMissionQueryService{

    private MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<MemberMission> findMemberMission(Long id){

        return memberMissionRepository.findById(id);
    }
}
