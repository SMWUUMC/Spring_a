package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    //2. 가게에 리뷰 추가하기 API
    Review createReview(Long memberId, Long StoreId, StoreRequestDTO.ReviewDTO request);

    Page<Review> getReviewList(Long StoreId, Integer page);

    // 3. 가게에 미션 추가하기 API
    Mission addMission(Long StoreId, StoreRequestDTO.MissionDTO request);
}
