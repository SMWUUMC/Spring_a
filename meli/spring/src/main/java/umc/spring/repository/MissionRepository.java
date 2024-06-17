package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;

// 9주차 3. 가게에 미션 추가하기 API
public interface MissionRepository extends JpaRepository<Mission, Long> {



}
