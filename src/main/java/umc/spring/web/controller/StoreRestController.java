package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    // 9주차 2. 가게에 리뷰 추가하기 API
    // 12주차 사진도 같이 업로드하기
    @PostMapping(value = "/{storeId}/addreviews", consumes = "multipart/form-data")
    // consumes = "multipart/form-data"
    // 위처럼 Controller를 수정하고 다시 swagger에 접속하면
    // swagger 자체도 변화되며 사진 업로드가 가능합니다!
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(
            //@RequestBody @Valid StoreRequestDTO.ReviewDTO request,
            @ModelAttribute @Valid StoreRequestDTO.ReviewDTO request,
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



    // 10주차 1. API 시그니처 만들기
    // 2) 컨트롤러에서 어떤 형태를 리턴하는지,
    // 어떤 파라미터가 필요한지, URI는 무엇인지
    // HTTP Method는 무엇인지만 정해둔다.
    @GetMapping("/{storeId}/reviews")
    // 스웨거에 API가 완성되지 않았음에도 명세를 해두는 이유는
    // 프론트엔드 개발자와의 개발 과정에서 병목을 최대한 줄이기 위함입니다.
    // 이게 무슨 소리냐 하면,
    // API 하나를 모두 완성 후 명세를 하게 되면
    // 프론트엔드 개발자는 해당 API가 완성이 될 때 까지
    // 다른 API의 응답을 모르기 때문에 작업을 멈추게 됩니다.
    // 이런 상황을 최대한 막기 위해
    // 우선적으로 응답 Data의 형태를 알려주어
    // 프론트 개발자도 미리 API 연결 부분을 작업 해둬
    // 최대한 개발을 병렬적으로 할 수 있도록 합니다.
    // 따라서 지금은 API 하나만 시그니처를 만들지만,
    // 되도록 많은 API에 대한 시그니처를 빠르게 만들 것을
    // 추천 드리며 이렇게 할 경우
    // 더 빠른 개발 속도를 기대할 수 있겠죠.
    // 이제 Controller 부분을 정의만 해두면서
    // 동시에 Swagger 명세를 합시다.
    @Operation(
            summary = "특정 가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요"
    )
    // @Operation은 이 API에 대한 설명을 넣게 되며
    // summary, description으로 설명을 적습니다.
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    // @ApiResponses로 이 API의 응답을 담게 되며
    // 내부적으로 @ApiResponse로 각각의 응답들을 담게 됩니다.
    // 에러 상황에 대해서만 content = 를 통해 형태를 알려줬고
    // (에러는 코드, 메세지만 중요하지 result는 필요 없어서!)
    // 보면 성공에 대해서는 content를 지정하지 않았습니다.
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    // @Parameters는 프론트엔드에서 넘겨줘야 할 정보를 담으며,
    // 위의 코드에선 일단 path variable만 기재했고,
    // API 완성 단계에서 query String도 추가할 것입니다.
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            //@CheckPage @RequestParam(name = "page") Integer page
            @RequestParam(name = "page") Integer page
    ) {
        // content가 없으면 ApiResponse<StoreResponseDTO.ReviewPreViewListDTO>
        // 여기서 StoreResponseDTO.ReviewPreViewListDTO가
        // 응답 형태로 보여지게 됩니다.

        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);

        //return null;
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));

    }



    // 10주차 2. 특정 가게의 미션 목록
    @GetMapping("{storeId}/missions")
    @Operation(
            summary = "특정 가게의 미션 목록 API",
            description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<StoreResponseDTO.MissionPreViewListDTO> getMissionList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            //@CheckPage @RequestParam(name = "page") Integer page
            @RequestParam(name = "page") Integer page
    ) {

        Page<Mission> missionList = storeQueryService.getMissionList(storeId, page);

        return ApiResponse.onSuccess(StoreConverter.missionPreViewListDTO(missionList));

    }

}
