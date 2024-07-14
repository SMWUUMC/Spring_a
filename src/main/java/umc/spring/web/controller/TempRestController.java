package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.TempConverter;
import umc.spring.service.TempService.TempQueryService;
import umc.spring.web.dto.TempResponse;

@RestController
// = @Controller + @ResponseBody
// - Json 형태로 객체 데이터를 반환한다
// - 데이터를 응답으로 제공하는 REST API를 개발할 때 주로 사용하며
// 객체를 ResponseEntity로 감싸서 반환한다
@RequestMapping("/temp")
// - 요청에 대해 어떤 Controller, 어떤 메소드가 처리할지를
// 맵핑하기 위한 어노테이션
// - 들어온 요청을 특정 메서드와 매핑하기 위해 사용
// - value : 요청받을 url 설정
// - method : 어떤 요청(GET, POST, PUT, DELETE)으로 받을지 정의
@RequiredArgsConstructor
// - final이나 @NonNull으로 선언된 필드만을
// 파라미터로 받는 생성자를 생성
/*
public class Person {
    private final String name;
    private final int age;
    private String address;

	public Person(final String name, final int age) {
    	this.name = name;
        this.age = age;
    }
}
 */
// @RestController, @RequiredArgsConstructor는
// Spring의 원리 중 하나인 의존성 주입이라는 것이다.
public class TempRestController {
// 딱히 비즈니스 로직이 들어가거나
// Database와 상호작용을 하는 것이 아니기에
// Service, Repository는 작성하지 않는다.

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    // - HTTP Get Method에 해당하는 단축 표현으로
    // 서버의 리소스를 조회할 때 사용한다
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    // 해당 경우는 Service를 사용할 것이기에
    // Controller는 우선 return null만 해둔다.
    // Springboot의 RestController에서
    // queryString을 받아오는 방법은 @RequestParam이다.
    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        //return null;

        tempQueryService.CheckFlag(flag);

        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }

}
