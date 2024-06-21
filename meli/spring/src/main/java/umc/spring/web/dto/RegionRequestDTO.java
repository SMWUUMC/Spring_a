package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class RegionRequestDTO {

    // 9주차 1. 특정 지역에 가게 추가하기 API
    @Getter
    public static class StoreDTO{
        @NotBlank
        String name;
        @NotBlank
        String address;
    }

}
