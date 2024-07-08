package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;

// 9주차 3. 가게에 미션 추가하기 API
public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 10주차 2. 특정 가게의 미션 목록
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);

}
