package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

// 10주차 4. 진행중인 미션 진행 완료로 바꾸기 API
public class MemberMissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompleteMemberMissionResultDTO{
        Long memberMissionId;
        Long memberId;
        Long missionId;
        MissionStatus status;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
    }

}
