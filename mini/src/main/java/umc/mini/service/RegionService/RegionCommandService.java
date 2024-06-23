package umc.mini.service.RegionService;

import umc.mini.domain.Store;
import umc.mini.web.dto.RegionRequestDTO;

public interface RegionCommandService {
    Store addStore(Long regionId, RegionRequestDTO.StoreDTO request);
}
