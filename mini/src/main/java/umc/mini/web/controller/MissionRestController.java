package umc.mini.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.mini.api.ApiResponse;
import umc.mini.converter.MissionConverter;
import umc.mini.domain.mapping.UserMission;
import umc.mini.service.MissionService.MissionCommandService;
import umc.mini.web.dto.MissionRequestDTO;
import umc.mini.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/{userId}/{missionId}/userMissions")
    public ApiResponse<MissionResponseDTO.CreateUserMissionResultDTO> challenge(
            @RequestBody MissionRequestDTO.UserMissionDTO request,
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "missionId") Long missionId
    ){

        UserMission userMission = missionCommandService.createUserMission(userId, missionId, request);

        return ApiResponse.onSuccess(MissionConverter.toCreateUserMissionResultDTO(userMission));

    }

}
