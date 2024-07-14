package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long id){
        return storeRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page){

        Store store = storeRepository.findById(storeId).get();

        // Controller를 보면, Page를 0번이 1 페이지라고 해 두었는데
        // 이에 대한 검증(page가 음수로 오는 경우)을
        // 커스텀 어노테이션을 이용해 처리할 수 있겠죠?
        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page - 1, 10));

        return storePage;

    }



    // 10주차 2. 특정 가게의 미션 목록
    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page){

        Store store = storeRepository.findById(storeId).get();

        Page<Mission> storePage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));

        return storePage;

    }

}
