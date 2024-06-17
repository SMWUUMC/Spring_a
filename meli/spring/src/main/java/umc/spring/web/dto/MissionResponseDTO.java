package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMemberMissionResultDTO{
        Long membermissionId;
        LocalDateTime createdAt;
    }

}
