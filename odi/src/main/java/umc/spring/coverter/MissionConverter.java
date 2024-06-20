package umc.spring.coverter;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDate;

// 4. 가게의 미션을 도전 중인 미션에 추가 (미션 도전하기) API
public class MissionConverter {

    public static MemberMission toMemberMission(MissionRequestDTO.memberMissionDTO request) {
        MissionStatus missionStatus = null;

        return MemberMission.builder()
                .status(missionStatus)
                .build();
    }

    public static MissionResponseDTO.addMemberMissionResultDTO toAddMissionResponseDTO(MemberMission memberMission) {
        return MissionResponseDTO.addMemberMissionResultDTO.builder()
                .memberId(memberMission.getId())
                .missionId(memberMission.getId())
                .createdAt(LocalDate.now())
                .build();
    }
}
