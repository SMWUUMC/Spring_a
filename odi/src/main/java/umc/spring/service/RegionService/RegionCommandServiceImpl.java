package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.coverter.RegionConverter;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.RegionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
//1. 특정 지역에 가게 추가하기 API
public class RegionCommandServiceImpl implements RegionCommandService{
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store addStore(RegionRequestDTO.addStoreDTO request){
        Store store = RegionConverter.toStore(request);

        return storeRepository.save(store);
    }
}
