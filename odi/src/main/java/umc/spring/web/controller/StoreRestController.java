package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.coverter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    // 2. 가게에 리뷰 추가하기 API
    @PostMapping("/{storeId}/{memberId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReviewDTO request,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);

        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }
    // 3. 가게에 미션 추가하기 API
    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDTO.addMissionResultDTO> addMission(@RequestBody @Valid StoreRequestDTO.MissionDTO request,
                                                                        @ExistStore @PathVariable(name = "storeId") Long storeId){
        Mission mission = storeCommandService.addMission(storeId, request);

        return ApiResponse.onSuccess(StoreConverter.toAddMissionResultDTO(mission));
    }
}
