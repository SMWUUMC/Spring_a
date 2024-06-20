package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

//1. 특정 지역에 가게 추가하기
public class RegionRequestDTO {

    @Getter
    public static class addStoreDTO{
        @NotBlank //Null, "", " " 모두 허용 X
        String name;

        @NotBlank
        String address;
    }
}
