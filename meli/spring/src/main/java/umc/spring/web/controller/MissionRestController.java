package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ChallengingMission;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
// - MemberMission을 추가하는 것이 목적
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{memberId}/{missionId}/membermissions")
    public ApiResponse<MissionResponseDTO.CreateMemberMissionResultDTO> challenge(
            @RequestBody @Valid MissionRequestDTO.MemberMissionDTO request,
            @ExistMember @PathVariable(name = "memberId") Long memberId,
            @ChallengingMission @ExistMission @PathVariable(name = "missionId") Long missionId
    ){

        MemberMission memberMission = missionCommandService.createMemberMission(memberId, missionId, request);

        return ApiResponse.onSuccess(MissionConverter.toCreateMemberMissionResultDTO(memberMission));

    }

}
