package umc.spring.coverter;

import umc.spring.domain.Store;
import umc.spring.web.dto.RegionRequestDTO;
import umc.spring.web.dto.RegionResponseDTO;

//1. 특정 지역에 가게 추가하기 API
public class RegionConverter {

    public static Store toStore(RegionRequestDTO.addStoreDTO request){ //RegionRequestDTO -> Store 변환
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }
    public static RegionResponseDTO.addStoreResultDTO addStoreResultDTO (Store store) { //Store -> RegionResponseDTO 변환
        return RegionResponseDTO.addStoreResultDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();

    }
}
