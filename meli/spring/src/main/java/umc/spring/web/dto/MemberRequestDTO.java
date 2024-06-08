package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO{
        @NotBlank
        // @NotBlank 는 null 과 "" 과 " " 모두 허용하지 않습니다.
        //@NotEmpty 에서 " " validation 이 추가된 것입니다.
        //즉, 세개 중 가장 validation 강도가 높은 것으로,
        // @NotBlank 는 null 과 "" 과 " " 모두 허용하지 않습니다.
        String name;
        @NotNull
        // Null만 허용하지 않는다.
        // 따라서, "" 이나 " " 은 허용하게 된다.
        // 그렇기 때문에 만약 "" (초기화된 String) )이나
        // " " (공백) 을 허용하지 않는다면 사용해서는 안된다.
        // Null 이 들어오게 되면,
        // 로직에 예상치 못한 오류가 발생하거나
        // 문제가 생길 경우 사용해야 합니다.
        //즉, 초기화나 공백의 값이 들어와 저장은 되야하지만
        // Null 로 들어온 경우
        // 오류가 나는 변수를 받을 때 사용하면 됩니다.
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        // @Size 는 name 의 최소, 최대 사이즈를 지정할 수 있으며
        // 해당 사이즈에 올바르지 않는 경우
        // @NotNull과 같이 message 를 담아 예외를 던질 수 있다.
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        // public @interface ExistCategories {
        List<Long> preferCategory;
    }

}
