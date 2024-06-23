package umc.mini.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.mini.api.ApiResponse;
import umc.mini.converter.RegionConverter;
import umc.mini.domain.Store;
import umc.mini.service.RegionService.RegionCommandService;
import umc.mini.web.dto.RegionRequestDTO;
import umc.mini.web.dto.RegionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionRestController {
    private final RegionCommandService regionCommandService;

    // 가게 생성 api
    @PostMapping("/{regionId}/stores")
    public ApiResponse<RegionResponseDTO.CreateStoreResultDTO> createStoreInRegion(
            @PathVariable Long regionId,
            @RequestBody RegionRequestDTO.StoreDTO requestDTO) {

        // regionId에 해당하는 지역에 가게 생성 요청 처리
        Store store = regionCommandService.addStore(regionId, requestDTO);

        return ApiResponse.onSuccess(RegionConverter.toCreateStoreResultDTO(store));
    }
}
