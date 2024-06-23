package umc.mini.converter;

import umc.mini.domain.Store;
import umc.mini.web.dto.RegionRequestDTO;
import umc.mini.web.dto.RegionResponseDTO;

import java.time.LocalDateTime;

public class RegionConverter {

    // Store 응답 -> Store 객체
    public static Store toStore(RegionRequestDTO.StoreDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    // Store 객체 -> Store 응답
    public static RegionResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store) {
        return RegionResponseDTO.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
