package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Region;

// 9주차 1. 특정 지역에 가게 추가하기 API
public interface RegionRepository extends JpaRepository<Region, Long> {



}
