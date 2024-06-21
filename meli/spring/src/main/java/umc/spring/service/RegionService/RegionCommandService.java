package umc.spring.service.RegionService;

import umc.spring.domain.Store;
import umc.spring.web.dto.RegionRequestDTO;

public interface RegionCommandService {

    // 9주차 1. 특정 지역에 가게 추가하기 API
    //Store createStore(Long memberId, Long regionId, RegionRequestDTO.StoreDTO request);
    Store createStore(Long regionId, RegionRequestDTO.StoreDTO request);

}
