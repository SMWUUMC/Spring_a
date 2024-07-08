package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.RegionConverter;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.RegionRequestDTO;

// 9주차 1. 특정 지역에 가게 추가하기 API
@Service
@Transactional
@RequiredArgsConstructor
public class RegionCommandServiceImpl implements RegionCommandService {

    private final StoreRepository storeRepository;
    //private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;

    @Override
    //public Store createStore(Long memberId, Long regionId, RegionRequestDTO.StoreDTO request){
    public Store createStore(Long regionId, RegionRequestDTO.StoreDTO request){

        Store store = RegionConverter.toStore(request);

        store.setRegion(regionRepository.findById(regionId).get());

        return storeRepository.save(store);

    }

}
