package umc.spring.service.StoreService;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    // 9주차 2. 가게에 리뷰 추가하기 API
    // 12주차 사진도 같이 업로드하기
    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request);



    // 9주차 3. 가게에 미션 추가하기 API
    Mission createMission(Long storeId, StoreRequestDTO.MissionDTO request);

}
