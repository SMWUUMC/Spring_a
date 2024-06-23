package umc.mini.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.mini.api.ApiResponse;
import umc.mini.converter.StoreConverter;
import umc.mini.domain.Review;
import umc.mini.service.StoreService.StoreCommandService;
import umc.mini.web.dto.StoreRequestDTO;
import umc.mini.web.dto.StoreResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    // 특정 가게에 리뷰 추가
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(
            @RequestBody @Valid StoreRequestDTO.ReveiwDTO request,
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "userId") Long userId){
        Review review = storeCommandService.createReview(userId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }


}
