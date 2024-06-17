package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
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

    // 9주차 2. 가게에 리뷰 추가하기 API
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(
            @RequestBody @Valid StoreRequestDTO.ReviewDTO request,
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            // public @interface ExistStore {
            // 이렇게 Request Body가 아닌 PathVariable 등
            // 다른 값에 대해서도 어노테이션을 붙일 수 있는데,
            // 이 경우는 @Valid를 거치지 않기에
            // 곧바로 `ConstraintViolationException` 가 전달이 됩니다.
            // 이를 이용해서 Path variable과 Query String의 값도 검증이 가능
            @ExistMember @RequestParam(name = "memberId") Long memberId
            // public @interface ExistMember {
            // @RequestParam
            // - 파라미터 이름으로 바인딩한다
            /*
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
             */
            // request-param?username=yum&age=20
            //위처럼 요청이 들어오면, username=yum, age=20이 바인딩 된다
            // @RequestParam 속성 name(value)
            // @RequestParam("username") String name
            // 위의 경우 url에서 username으로 받은 파라미터가 name 변수에 저장된다
            // 만약 HTTP 파라미터 이름이 변수 이름과 같은 경우 name(value) 속성은 생략 가능하다
    ) {

        Review review = storeCommandService.createReview(memberId, storeId, request);

        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));

    }



    // 9주차 3. 가게에 미션 추가하기 API
    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDTO.CreateMissionResultDTO> createMission(
            @RequestBody @Valid StoreRequestDTO.MissionDTO request,
            @ExistStore @PathVariable(name = "storeId") Long storeId
    ) {

        Mission mission = storeCommandService.createMission(storeId, request);

        return ApiResponse.onSuccess(StoreConverter.toCreateMissionResultDTO(mission));

    }

}
