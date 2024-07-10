package umc.spring.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apiPayload.code.BaseCode;
import umc.spring.apiPayload.code.status.SuccessStatus;

// API 응답 통일
// 응답의 경우 Code라는 이름의 enum으로 그 형태를 관리한다.
// 이 때, 성공 응답과 실패 응답을 하나의 enum으로 관리할 수도 있고,
// 분리할 수도 있다.
// 해당 예시는 하나의 enum으로 관리하는 것이다.

@Getter
@AllArgsConstructor
// - 모든 필드 값을 파라미터로 받는 생성자를 생성
/*
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
    	this.name = name;
        this.age = age;
    }
}
 */
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
// - Json 직렬화 순서를 제어
/*
//적용전
{
  "id": 1,
  "name": "name"
}
//적용후
{
  "name": "name",
  "id": 1
}
 */
public class ApiResponse<T> {
// 제너릭 (Generic)
// - 쉽게 타입을 파라미터로 가지는 클래스와 인터페이스이며
// 이름 뒤에 <>가 붙는다.

// 제너릭의 장점
// - 컴파일 시에 강한 타입 체크를 할 수 있다.
// 컴파일 시점에 타입 체크를 강제화 할 수 있고
// 이 말은 자연스럽게 코드의 정확성이 높아진다는 장점이 된다.
// - Casting 제거한다.
// 캐스팅은 형 변환을 위한 방법 중 하나인데,
// 클래스에 어떤 타입이 올 지 모르니 Object 타입으로 받아서
// Object 타입으로 반환시키면 사용할 때에 형변환 과정이
// 필수적으로 이루어져야 한다.
// 하지만 제너릭을 사용한다면 형변환의 불필요한 오버헤드를 줄일 수 있어
// 전체 성능을 조금이나마 상승시킬 수 있게 된다.
// 이렇게 제너릭을 사용한다면 조금 더 우아한 코드를 생성할 수 있게 된다.

// API의 응답 정보를 정한다는 의미의 이름
// API 응답 통일을 위한 ApiResponse 클래스
// 통일된 API 응답을 위한 class

    /*
    {
    	isSuccess : Boolean
	    code : String
	    message : String
	    result : {응답으로 필요한 또 다른 json}
    }

    {
        "isSuccess ": true,
        "code" : "2000",
        "message" : "OK",
        "result" :
            {
                "testString" : "This is test!"
            }
    }
     */
    @JsonProperty("isSuccess")
    // - @JsonProperty 어노테이션으로 객체를 JSON 형식으로 변환할 때
    // Key의 이름을 설정할 수 있다.
    private final Boolean isSuccess;
    // 성공인지 아닌지 알려주는 필드
    private final String code;
    // HTTP 상태코드로는 너무 제한적인 정보만 줄 수 있어서
    // 조금 더 세부적인 응답 상황을 알려주기 위한 필드
    private final String message;
    // code에 추가적으로 우리에게 익숙한 문자로 상황을 알려주는 필드
    @JsonInclude(JsonInclude.Include.NON_NULL)
    // null인 데이터를 제외한 데이터를 JSON으로 변환한다
    private T result;
    // result는 어떤 형태의 값이 올지 모르기에 Generic으로 만들어 준다.
    // 실제로 클라이언트에게 필요한 데이터가 담긴다.
    // 보통 에러 상황에는 null을 담지만,
    // null을 담지 않는 에러 상황도 있다.

    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), result);
    }
    // 제너릭 메서드
    // - 매개 타입과 리턴 타입으로 타입 파라미터를 갖는 메서드이다.
    // - 보통 클래스를 선언하지 않고 쓸 수 있는 static 메서드에
    // 자주 쓰이곤 하는데 그도 그럴 것이,
    // 클래스를 선언하지 않으면 제너릭을 사용할 해당 클래스가
    // 어떤 클래스를 받을 수 있는지 직접 지정할 수 없기 때문에
    // 메서드 단위에서 컴파일 체크를 해줄 수 있게 하기 위해 사용된다.

    public static <T> ApiResponse<T> of(BaseCode code, T result){
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), result);
    }

    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(true, code, message, data);
    }

}
