package umc.spring.service.RegionService;

import umc.spring.domain.Region;

import java.util.Optional;

// 9주차 1. 특정 지역에 가게 추가하기 API
public interface RegionQueryService {

    Optional<Region> findRegion(Long id);

}
