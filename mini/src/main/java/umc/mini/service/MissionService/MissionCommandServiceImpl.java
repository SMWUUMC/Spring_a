package umc.mini.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mini.converter.MissionConverter;
import umc.mini.domain.mapping.UserMission;
import umc.mini.repository.MissionRepository;
import umc.mini.repository.UserMissionRepository;
import umc.mini.repository.UserRepository;
import umc.mini.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public UserMission createUserMission(Long userId, Long missionId, MissionRequestDTO.UserMissionDTO request){

        UserMission userrMission = MissionConverter.toUserMission(request);

        userrMission.setUser(userRepository.findById(userId).get());
        userrMission.setMission(missionRepository.findById(missionId).get());

        return userMissionRepository.save(userrMission);

    }
}
