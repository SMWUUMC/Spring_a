package umc.mini.service.StoreService;

import umc.mini.domain.Review;
import umc.mini.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    // 리뷰 생성
    Review createReview(Long userId, Long storeId, StoreRequestDTO.ReveiwDTO request);

}
