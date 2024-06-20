package umc.spring.service.RegionService;

import umc.spring.domain.Store;
import umc.spring.web.dto.RegionRequestDTO;

//1. 특정 지역에 가게 추가하기 API
public interface RegionCommandService {
    Store addStore(RegionRequestDTO.addStoreDTO request);
}
