package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

// 화면 렌더링 시 음식 카테고리를 조회하는 API를 호출하고,
// 그 API의 결과에서 음식 카테고리의 id값을
// 프론트엔드가 넘겨준다는 것을 전제
public class MemberRequestDTO {

    @Getter
    public static class JoinDTO{
        // @NotBlank, @NotNull 등등 이런 어노테이션은
        // java에서 제공하는 검증을 위한 어노테이션이며
        // @ExistCategories처럼 커스텀 어노테이션과 같이
        // 사용이 가능합니다!
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
        // 유저가 선호하는 카테고리를 처리
        // @ExistCategories 어노테이션이 Request Body로 받아 올
        // DTO(MemberRequestDTO.JoinDTO)의
        // preferCategory필드에 붙어있으므로
        // MemberRestController 컨트롤러에서
        // RequestBody를 받아오는 과정에서
        // @ExistCategories가 붙어있는
        // MemberRequestDTO.JoinDTO.preferCategory로 인해
        // CategoriesExistValidator.isValid의
        // ConstraintValidatorContext 코드가 실행된다.
        List<Long> preferCategory;
    }

}
