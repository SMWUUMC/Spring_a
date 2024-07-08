package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);

    Page<Review> getReviewList(Long storeId, Integer page);
    // 위의 Page는 Spring Data JPA에서 제공하는
    // Paging을 위한 추상화를 제공합니다.
    // Page 자체에 페이징과 관련된 여러 정보가 담기게 되며
    // 위에서 작성한 DTO에서 그 흔적을 찾아볼 수 있습니다.



    // 10주차 2. 특정 가게의 미션 목록
    Page<Mission> getMissionList(Long storeId, Integer page);

}
