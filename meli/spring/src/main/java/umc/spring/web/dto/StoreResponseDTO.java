package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StoreResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResultDTO{
        Long reviewId;
        LocalDateTime createdAt;
    }



    // 9주차 3. 가게에 미션 추가하기 API
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionResultDTO{
        Long missionId;
        LocalDateTime createdAt;
    }



    // 10주차 1. API 시그니처 만들기
    // 1) DTO 작성 : 응답과 요청 DTO를 만든다.
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
    // 위의 코드를 보면, static class로 DTO를 만들었습니다.
    // 위의 사진처럼 DTO 클래스 파일을 줄여서
    // 파일 아키텍처 가독성을 높이기 위함입니다.
    // 자잘한 DTO를 전부 다 하나의 클래스로 만들면,
    // 저 DTO 파일을 펼쳐서 필요한 클래스를 찾을 때,
    //찾기가 힘들고 프로젝트 구조를 알아보기가 힘들어집니다.
    // 그래서 큰 단위의 DTO를 하나의 클래스로 두고
    // 하위 자잘한 DTO들은 static class로 둡니다.

        List<ReviewPreViewDTO> reviewList;
        // 우선 ‘목록’이기 때문에 리뷰의 정보들의 목록이 필요합니다.
        // 그래서 List<ReviewDetailDTO> 이렇게
        // 리뷰의 정보를 담은 DTO를
        // List로 담은 또 다른 DTO를 만들었습니다.
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
    // 리뷰의 정보를 담은 DTO
    // ReviewPreViewDTO에는 위에서 말한 4개의 정보를 담았으며,
    // 여기서 작성한 사용자의 정보가 닉네임 말고도 더 많았다면,
    // 그 자체로 DTO로 구성을 하는 것이 좋습니다.
    /*
    // 이런 느낌으로?

    MemberInfoDTO memberinfo;
    // 작성한 사용자의 정보가 닉네임 말고도 더 많은 경우
    Float score;
    // 2. 리뷰의 점수
    String body;
    // 4. 리뷰의 상세 내용
    LocalDate createdAt;
    // 3. 리뷰가 작성된 날짜
     */

        // 목록 조회 API를 만들기 위해 필요한 정보 알아보기!
        String ownerNickname;
        // 1. 닉네임
        // 지금은 MemberInfoDTO와 같이 따로 DTO로 만들기에는
        // 닉네임 하나여서 굳이 따로 감쌀 필요는 없다.
        Float score;
        // 2. 리뷰의 점수
        String body;
        // 4. 리뷰의 상세 내용
        LocalDate createdAt;
        // 3. 리뷰가 작성된 날짜

    }



    // 10주차 2. 특정 가게의 미션 목록
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {

        List<StoreResponseDTO.MissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {

        Integer reward;
        String ownerStorename;
        String missionSpec;
        LocalDate createdAt;

    }

}
