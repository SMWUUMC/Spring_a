package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.RegionRequestDTO;
import umc.spring.web.dto.RegionResponseDTO;

import java.time.LocalDateTime;

// 9주차 1. 특정 지역에 가게 추가하기 API
public class RegionConverter {

    public static Store toStore(RegionRequestDTO.StoreDTO request){
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    public static RegionResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store){
        return RegionResponseDTO.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
