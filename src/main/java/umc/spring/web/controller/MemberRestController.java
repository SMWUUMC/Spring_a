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
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
// 입력 파라미터의 유효성 검증은
// 컨트롤러에서 최대한 처리하고 넘겨주는 것이 좋다.
// 하지만 개발을 하다보면 불가피하게 다른 곳에서
// 파라미터를 검증해야 할 수 있다.
// Spring에서는 이를 위해 AOP 기반으로 메소드의 요청을 가로채서
// 유효성 검증을 진행해주는 @Validated를 제공하고 있다.
// @Validated는 JSR 표준 기술이 아니며
// Spring 프레임워크에서 제공하는 어노테이션 및 기능이다.
// 다음과 같이 클래스에 @Validated를 붙여주고,
// 유효성을 검증할 메소드의 파라미터에
// @Valid를 붙여주면 유효성 검증이 진행된다.
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // CategoriesExistValidator의 isValid 메소드에서
    // false를 리턴하면 ConstraintViolationException을
    // 발생시키는데, MemberRestController.join에서
    // @Valid 어노테이션이 존재하므로 MemberRequestDTO의
    // @ExistCategories에서 발생한 예외가 바로 전달되지 않고,
    // @Valid 어노테이션이
    // MethodArgumentNotValidException을 발생시킵니다.
    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request){
    // @Valid
    // @Valid는 JSR-303 표준 스펙(자바 진영 스펙)으로써
    // 빈 검증기(Bean Validator)를 이용해
    // 객체의 제약 조건을 검증하도록 지시하는 어노테이션이다.
    // JSR 표준의 빈 검증 기술의 특징은
    // 객체의 필드에 달린 어노테이션으로 편리하게 검증을 한다는 것이다.
    // Spring에서는 일종의 어댑터인
    // LocalValidatorFactoryBean가 제약 조건 검증을 처리한다.
    // 이를 이용하려면 LocalValidatorFactoryBean을
    // 빈으로 등록해야 하는데,
    // SpringBoot에서는 아래 의존성만 추가해주면
    // 해당 기능들이 자동 설정된다.
    // implementation group: 'org.springframework.boot', name: 'spring-boot-starter-validation'
    // @Valid는 기본적으로 컨트롤러에서만 동작하며
    // 기본적으로 다른 계층에서는 검증이 되지 않는다.
    // 다른 계층에서 파라미터를 검증하기 위해서는
    // @Validated와 결합돼야 한다.

    // @RequestBody
    // 비동기통신을 하기위해서는
    // 클라이언트에서 서버로 요청 메세지를 보낼 때,
    // 본문에 데이터를 담아서 보내야 하고,
    // 서버에서 클라이언트로 응답을 보낼때에도
    // 본문에 데이터를 담아서 보내야 한다.
    //이 본문이 바로 body 이다.
    //즉, 요청본문 requestBody,
    // 응답본문 responseBody을 담아서 보내야 한다.
    // 이때 본문에 담기는 데이터 형식은 여러가지 형태가 있겠지만
    // 가장 대표적으로 사용되는 것이 JSON 이다.
    // 즉, 비동기식 클라-서버 통신을 위해
    // JSON 형식의 데이터를 주고받는 것이다.
    // 요청 본문(request body)에 담긴 값을 자바객체로 변환
    // @RequestBody를 통해서 자바객체로 conversion을 하는데,
    // 이때 HttpMessageConverter를 사용한다.
    //@ResponseBody가 붙은 파라미터에는
    // HTTP 요청의 분문 body 부분이 그대로 전달된다.
    // RequestMappingHandlerAdpter에는
    // HttpMessageConverter 타입의 메세지 변환기가
    // 여러개 등록되어 있다.
    //@RequestBody 어노테이션이 붙은 파라미터에는
    // http요청의 본문(body)이 그대로 전달된다.
    // 일반적인 GET/POST의 요청 파라미터라면
    // @RequestBody를 사용할 일이 없을 것이다.
    // 반면에 xml이나 json기반의 메시지를 사용하는 요청의 경우에
    // 이 방법이 매우 유용하다.
    // HTTP 요청의 바디내용을 통째로 자바객체로 변환해서
    // 매핑된 메소드 파라미터로 전달해준다.

        Member member = memberCommandService.joinMember(request);

        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));

    }



    // 10주차 1. 내가 작성한 리뷰 목록 API
    @GetMapping("{memberId}/reviews")
    @Operation(
            summary = "내가 작성한 리뷰 목록 API",
            description = "내가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistMember @PathVariable(name = "memberId") Long memberId,
            //@CheckPage @RequestParam(name = "page") Integer page
            @RequestParam(name = "page") Integer page
    ) {

        Page<Review> reviewList = memberQueryService.getReviewList(memberId, page);

        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviewList));

    }

    // 10주차 3. 내가 진행중인 미션 목록 API
    @GetMapping("{memberId}/missions")
    @Operation(
            summary = "내가 진행중인 미션 목록 API",
            description = "내가 진행중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다.")
    })
    public ApiResponse<MemberResponseDTO.MemberMissionPreViewListDTO> getMemberMissionChallengingList(
            @ExistMember @PathVariable(name = "memberId") Long memberId,
            //@CheckPage @RequestParam(name = "page") Integer page
            @RequestParam(name = "page") Integer page
    ) {

        Page<MemberMission> memberMissionList = memberQueryService.getMemberMissionChallengingList(memberId, page);

        return ApiResponse.onSuccess(MemberConverter.memberMissionPreViewListDTO(memberMissionList));

    }

}
