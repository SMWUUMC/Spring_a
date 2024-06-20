package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.coverter.MissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
// 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{memberId}/{missionId}/memberMissions")
    public ApiResponse<MissionResponseDTO.addMemberMissionResultDTO> addMemberMission(@RequestBody @Valid MissionRequestDTO.memberMissionDTO request,
                                                                                      @ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                                      @ExistMission @RequestParam(name = "missionId") Long missionId) {
        MemberMission memberMission = missionCommandService.addMemberMission(memberId, missionId, request);

        return ApiResponse.onSuccess(MissionConverter.toAddMissionResponseDTO(memberMission));
    }
}
