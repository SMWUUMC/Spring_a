package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RegionConverter;
import umc.spring.domain.Store;
import umc.spring.service.RegionService.RegionCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.web.dto.RegionRequestDTO;
import umc.spring.web.dto.RegionResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionRestController {

    private final RegionCommandService regionCommandService;

    // 9주차 1. 특정 지역에 가게 추가하기 API
    @PostMapping("/{regionId}/stores")
    public ApiResponse<RegionResponseDTO.CreateStoreResultDTO> createStore(
            @RequestBody @Valid RegionRequestDTO.StoreDTO request,
            @ExistRegion @PathVariable(name = "regionId") Long regionId
    ) {

        Store store = regionCommandService.createStore(regionId, request);

        return ApiResponse.onSuccess(RegionConverter.toCreateStoreResultDTO(store));

    }

}
