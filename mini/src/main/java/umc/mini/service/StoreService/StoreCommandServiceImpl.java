package umc.mini.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mini.converter.StoreConverter;
import umc.mini.domain.Review;
import umc.mini.repository.ReviewRepository;
import umc.mini.repository.StoreRepository;
import umc.mini.repository.UserRepository;
import umc.mini.web.dto.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final StoreRepository storeRepository;

    // 리뷰 생성
    @Override
    public Review createReview(Long userId, Long storeId, StoreRequestDTO.ReveiwDTO request) {

        Review review = StoreConverter.toReview(request);

        review.setUser(userRepository.findById(userId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }
}
