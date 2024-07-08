package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Review toReview(StoreRequestDTO.ReviewDTO request) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }



    // 9주차 3. 가게에 미션 추가하기 API
    public static Mission toMission(StoreRequestDTO.MissionDTO request) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();
    }

    public static StoreResponseDTO.CreateMissionResultDTO toCreateMissionResultDTO(Mission mission){
        return StoreResponseDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }



    // 10주차 1. API 시그니처 만들기
    // 3) Converter 겉 모양(정의만) 작성 해두기
    // : 컨버터 정의만 해둔다.

    // 여기서 converter를 보면,
    // ListDTO를 위해 리스트에 들어갈 DTO를
    // 다른 Converter에서 제작해서 이를 Java stream을 통해
    // DTO의 List를 만드는 것을 알 수 있습니다.
    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){

        //return null;
        // 보시면 아시겠지만 일단 return null 해두고
        // 서비스의 매서드를 만들면서 완성(필요하면) 하거나
        // 서비스 메서드 완성 후 세부 로직을 구현해도 됩니다.
        // 지금 저 컨버터의 경우는
        // 그저 응답을 위한 Entity to DTO이기에
        // 서비스 로직 완성 후 작성을 해도 되겠지만,
        // 만약 클라이언트의 요청 데이터를
        // JPA에서 처리하기 위한 Entity로 만드는,
        // DTO to Entity의 경우는 복잡해질 가능성이 높고
        // 연관관계 처리를 서비스에서 하는 것이
        // 좋은 경우가 많기 때문에,
        // 그런 경우는 서비스 로직 작성을 하는 과정에서
        // 컨버터를 완성을 해야하는 경우도 있습니다.

        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                // .ownerNickname(review.getMember().getName())
                // 이 코드를 통해 review에 @MantyToOne으로 지정해둔
                // Member를 통해 아주 편하게 데이터를 가져오는 것을
                // 확인 할 수 있습니다.
                // 이는 객체 그래프 탐색 이라는
                // Spring Data JPA에서 사용 가능한
                // 아주 강력한 기능입니다.
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();

    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        //return null;

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();

    }



    // 10주차 2. 특정 가게의 미션 목록
    public static StoreResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission){

        return StoreResponseDTO.MissionPreViewDTO.builder()
                .reward(mission.getReward())
                .ownerStorename(mission.getStore().getName())
                .missionSpec(mission.getMissionSpec())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();

    }

    public static StoreResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList){

        List<StoreResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(StoreConverter::missionPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();

    }

}
