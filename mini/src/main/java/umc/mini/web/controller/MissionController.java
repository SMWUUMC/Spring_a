package umc.mini.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.mini.api.ApiResponse;
import umc.mini.service.TempService.MissionService;
import umc.mini.web.dto.MissionResponse;

import java.util.List;

// 유저의 미션에 대한 컨트롤러
@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    // 1. 미션 상태에 따른 미션 목록 조회 api
    private final MissionService missionService;

    @GetMapping
    public ApiResponse<List<MissionResponse>> getMissionsByStatus(@RequestParam(defaultValue = "challenging") String status) {

        // 예외처리 - MissionError(BAD_REQUEST)
        if (!status.equals("challenging") && !status.equals("completed")) {
            throw new IllegalArgumentException(status + " 이라는 status는 정의되지 않음");
        }

        List<MissionResponse> missions = missionService.getMissionsByStatus(status);
        return ApiResponse.onSuccess(missions);
    }

    // 1-2. 미션 성공 상태 변경 api
    //
}
