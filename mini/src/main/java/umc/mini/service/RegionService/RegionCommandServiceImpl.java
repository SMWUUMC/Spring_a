package umc.mini.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.mini.converter.RegionConverter;
import umc.mini.domain.Region;
import umc.mini.domain.Store;
import umc.mini.repository.RegionRepository;
import umc.mini.repository.StoreRepository;
import umc.mini.web.dto.RegionRequestDTO;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegionCommandServiceImpl implements RegionCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public Store addStore(Long regionId, RegionRequestDTO.StoreDTO request) {
        // 가게 형식 매핑
        Store store = RegionConverter.toStore(request);

        // regionId에 해당하는 Region 객체 가져오기
        Optional<Region> optionalRegion  = regionRepository.findById(regionId);

        // 예외 처리
        if (optionalRegion.isEmpty()) {
            throw new RuntimeException("Region not found for id: " + regionId);
        }

        Region region = optionalRegion.get();
        store.setRegion(region);

        return storeRepository.save(store);
    }
}
