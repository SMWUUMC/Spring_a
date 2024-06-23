package umc.mini.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class RegionRequestDTO {

    @Getter
    public static class StoreDTO{

        // 가게 이름
        @NotBlank
        String name;

        @NotBlank
        String address;
    }
}
