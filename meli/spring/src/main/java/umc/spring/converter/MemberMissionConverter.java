package umc.spring.converter;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionResponseDTO;

import java.time.LocalDateTime;

// 10주차 4. 진행중인 미션 진행 완료로 바꾸기 API
public class MemberMissionConverter {

    public static MemberMissionResponseDTO.CompleteMemberMissionResultDTO toCompleteMemberMissionResultDTO(MemberMission memberMission){
        return MemberMissionResponseDTO.CompleteMemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus())
                .createdAt(memberMission.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
