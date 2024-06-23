package umc.mini.service.MissionService;

import umc.mini.domain.mapping.UserMission;
import umc.mini.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    UserMission createUserMission(Long userId, Long missionId, MissionRequestDTO.UserMissionDTO request);
}
