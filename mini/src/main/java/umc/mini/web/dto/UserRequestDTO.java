package umc.mini.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class JoinDto{
        // 유저 이름
        String name;
        // 유저 성별
        Integer gender;
        // 유저 생년월일?
//        Integer birthYear;
//        Integer birthMonth;
//        Integer birthDay;
        // 유저 주소
        String address;
        // 유저 상세 주소
        String specAddress;
        // 유저의 선호 음식 카테고리
        List<Long> preferCategory;
    }
}
