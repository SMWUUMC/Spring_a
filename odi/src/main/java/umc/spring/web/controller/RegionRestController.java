package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.coverter.RegionConverter;
import umc.spring.domain.Store;
import umc.spring.service.RegionService.RegionCommandService;
import umc.spring.web.dto.RegionRequestDTO;
import umc.spring.web.dto.RegionResponseDTO;


//1. 특정 지역에 가게 추가하기 API
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionRestController {

    private final RegionCommandService regionCommandService;

    @PostMapping("/{regionId}/stores")
    public ApiResponse<RegionResponseDTO.addStoreResultDTO> join(@RequestBody @Valid RegionRequestDTO.addStoreDTO request){
        Store store = regionCommandService.addStore(request);
        return ApiResponse.onSuccess(RegionConverter.addStoreResultDTO(store));
    }
}
