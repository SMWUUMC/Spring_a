package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


// 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
public class MissionRequestDTO {

    @Getter
    public static class memberMissionDTO{
        @NotBlank
        String status;
    }
}
