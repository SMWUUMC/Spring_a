package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistMission;

import java.util.List;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
// - MemberMission을 추가하는 것이 목적
public class MissionRequestDTO {

    @Getter
    public static class MemberMissionDTO{
        @NotNull
        Integer missionStatus;
    }

}
