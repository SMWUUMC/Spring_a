package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }



    // 10주차 1. 내가 작성한 리뷰 목록 API
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {

        List<MemberResponseDTO.ReviewPreViewDTO> reviewList;
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

    // 10주차 3. 내가 진행중인 미션 목록 API
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionPreViewListDTO {

        List<MemberResponseDTO.MemberMissionPreViewDTO> memberMissionList;
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
    public static class MemberMissionPreViewDTO {

        Integer reward;
        MissionStatus status;
        String ownerStorename;
        String missionSpec;
        LocalDate createdAt;

    }

}
