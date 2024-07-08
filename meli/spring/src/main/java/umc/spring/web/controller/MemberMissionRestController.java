package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.validation.annotation.ExistMemberMission;
import umc.spring.web.dto.MemberMissionResponseDTO;

// 10주차 4. 진행중인 미션 진행 완료로 바꾸기 API
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/membermissions")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @GetMapping("{membermissionId}/complete")
    @Operation(
            summary = "진행중인 미션 진행 완료로 바꾸기 API",
            description = "진행중인 미션을 진행 완료로 바꾸는 API입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "membermissionId", description = "멤버미션의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberMissionResponseDTO.CompleteMemberMissionResultDTO> completeMemberMission(
            @ExistMemberMission @PathVariable(name = "membermissionId") Long membermissionId
    ) {

        MemberMission memberMission = memberMissionCommandService.completeMemberMission(membermissionId);

        return ApiResponse.onSuccess(MemberMissionConverter.toCompleteMemberMissionResultDTO(memberMission));

    }

}
