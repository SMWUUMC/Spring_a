package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Review createReview(Long memberId, Long StoreId, StoreRequestDTO.ReveiwDTO request);

    Page<Review> getReviewList(Long StoreId, Integer page);
}
