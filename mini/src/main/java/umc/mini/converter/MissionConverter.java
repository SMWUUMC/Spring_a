package umc.mini.converter;

import umc.mini.domain.enums.MissionStatus;
import umc.mini.domain.mapping.UserMission;
import umc.mini.web.dto.MissionRequestDTO;
import umc.mini.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    // MemberMission 요청 -> MemberMission 객체
    public static UserMission toUserMission(MissionRequestDTO.UserMissionDTO request){

        MissionStatus missionStatus = null;

        switch(request.getMissionStatus()){
            case 1:
                missionStatus = MissionStatus.CHALLENGING;
                break;
            case 2:
                missionStatus = MissionStatus.COMPLETED;
                break;
        }

        return UserMission.builder()
                .status(missionStatus)
                .build();

    }

    // MemberMission 객체 -> MemberMission 응답
    public static MissionResponseDTO.CreateUserMissionResultDTO toCreateUserMissionResultDTO(UserMission userMission){
        return MissionResponseDTO.CreateUserMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
