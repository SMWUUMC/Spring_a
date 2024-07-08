package umc.spring.converter;

import umc.spring.web.dto.TempResponse;

public class TempConverter {
// Converter
// - 스프링은 자동으로 타입에 맞게 바인딩되도록 타입 변환을 해주는
// 인터페이스 Converter를 구현한 클래스들을 제공한다.
// - 스프링이 제공하는 Converter 구현 클래스들을 통해
// 우리는 자유롭게 HTTP 요청 파라미터로 넘어온 문자들을
// 원하는 타입으로 바인딩하여 사용할 수 있다.

    public static TempResponse.TempTestDTO toTempTestDTO(){
        return TempResponse.TempTestDTO.builder()
                .testString("This is Test!")
                .build();
    }

    // 컨버터 함수 이름은 to<만들려는 대상> 이렇게 지어준다.
    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag) {
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }

}
