package umc.spring.converter;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
public class MissionConverter {

    // MemberMission 객체를 만드는 작업 (클라이언트가 준 DTO to Entity)
    public static MemberMission toMemberMission(MissionRequestDTO.MemberMissionDTO request){

        MissionStatus missionStatus = null;

        switch(request.getMissionStatus()){
            case 1:
                missionStatus = MissionStatus.CHALLENGING;
                break;
            case 2:
                missionStatus = MissionStatus.COMPLETE;
                break;
        }

        return MemberMission.builder()
                .status(missionStatus)
                .build();

    }

    public static MissionResponseDTO.CreateMemberMissionResultDTO toCreateMemberMissionResultDTO(MemberMission memberMission){
        return MissionResponseDTO.CreateMemberMissionResultDTO.builder()
                .membermissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
