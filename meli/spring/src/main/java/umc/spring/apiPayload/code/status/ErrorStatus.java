package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
// status들은 enum이다.
// Enum 관리에 대한 고찰
// - Code를 아무 생각 없이 관리할 수도 있지만,
// Code도 규칙을 정해두면 프론트엔드 개발자와 소통이 용이해진다.
// - 무엇보다, Springboot 자체가 대규모 프로젝트를 위한 프레임워크임을 고려한다면,
// Code 자체도 체계적으로 관리하는 것이
// 대규모 프로젝트에서 변경에 대해 빠르게 대처할 수 있을 것이다.
// 이는 Springboot의 목적에도 부합한다.
public enum ErrorStatus implements BaseErrorCode {
// API 응답에 들어갈 code

    // HTTP 상태 코드
    // 400번 대 : 클라이언트 측 잘못으로 인한 에러
    // 404 : NotFound : 요청한 정보가 그냥 없음
    // 500번 대 : 서버 측 잘못으로 인한 에러
    // 504 : Gateway Timeout : 서버가 응답을 안 줌 (그냥 터진거긴 함...)

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    // 500 : Internal Server Error : 서버 터짐...
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    // 400 : Bad Request : 요청 이상하게 함, 필요한 정보 누락됨...
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    // 401 : Unauthorized : 인증이 안됨 (로그인이 되어야 하는데 안된 상황)
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    // 403 : Forbidden : 권한 없음 (로그인은 되었으나 접근이 안됨, 관리자 페이지 등등)
    // 사실 HTTP 상태 코드는 더욱 많지만
    // 403인 상황에서 왜 권한이 없는지 더 자세히 알려주면
    // 클라이언트 입장에서도 더 빠르게 문제를 찾아낼 수 있다
    // 그래서 API 응답에
    // 자체적인 우리만의 code와 message를 도입하는 것이다.

    // - 위 코드 자체로 프론트엔드 개발자가 어떤 상황인지는
    // 대략적으로 파악이 가능하다
    // - HTTP 프로토콜의 Status 자체로도 의미가 있기 때문이다.
    // - 하지만 대략적인 상황만 파악이 가능하면 좋지 않다.
    // - 예를 들어 응답의 HTTP Status가 400번대라고 하자.
    // - 그러면 프론트엔드 개발자는 자신이 잘못한 건 파악했지만
    // 정확히 어떤 잘못이고 무엇 때문에 API 응답이 저런 지는 모를 것이다.
    // - 그렇다면, 매번 백엔드 개발자에게 직접 물어봐야하지만,
    // 이는 서로에게 너무나도 번거로운 일이니 좋지 않다.
    // - 따라서, 우리(서버)는 두 번째 인자로
    // 자체적인 String으로 code를 제공함으로써
    // 프론트엔드 개발자가 더 정확히 API 응답의 원인을 알 수 있도록 한다.
    // - 그렇다면 아래처럼 하는 것이 좋을까?
    /*
    "4002" -> 회원 정보 누락
    "4003" -> JWT 토큰 안줌
    "4004" -> 회원가입 닉네임 누락
    "4005" -> 게시글 없음
    .....
     */
    // - 우선 4000번대를 쓰는 이유는 400번대 에러임을 나타내는 것이다.
    // - 만약 400번대를 사용한다면 400~499까지
    // 99개의 에러 코드만 만들 수 있어,
    // 이는 너무 제한적이기에 4000번대를 쓰는 것이다.
    // - 프로젝트 규모가 커질 수도 있기에
    // 400~499 이렇게 너무 적은 경우만 가능하도록 설계를 하는 것은
    // 매우 좋지 않다.
    // - 또한 위의 경우는 일관성이 없다.
    // - 4002는 회원 관련이었다가, 갑자기 4003은 토큰 관련이기 때문이다.
    // - 이러면 프론트엔드 개발자 입장에서 매우 헷갈린다.
    // - 따라서 이러한 에러에 대한 code도 체계적인 규칙을 두는 것이 좋다.
    // - 아래는 추천 규칙이다.
    /*
    1. common 에러는 COMMON000 으로 둔다.
        <- 잘 안쓰지만 마땅하지 않을 때 사용
    2. 관련된 경우마다 code에 명시적으로 표현한다.
        - 예를 들어 멤버 관련이면 MEMBER001 이런 식으로
    3. 2번에 이어서 4000번대를 붙인다.
       서버측 잘못은 그냥 COMMON 에러의 서버 에러를 쓰면 됨.
        - MEMBER400_1 아니면 MEMBER4001 이런 식으로
     */
    // - 위와 같은 규칙으로 Code enum을 관리하는 것이
    // 확장성 측면에서도 매우 좋다.
    // - 위의 규칙을 적용하여 예시로 Code enum을 몇 개 추가한다.

    // 예외 상황 추가하기 (ErrorStatus)
    // 멤버 관련 응답
    // 멤버 관리 에러
    // Member Error
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // ~~~ 관련 응답 ...
    // Article Error
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),
    // 이런 느낌으로 프로젝트를 진행하다가 생기는 예외 상황에 대해
    // 추가를 하면 된다.

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),



    // FoodCategory Error
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY4001", "음식 카테고리가 없습니다."),

    // Store Error
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_4001","가게가 없습니다."),

    // 9주차 1. 특정 지역에 가게 추가하기 API
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION_4001", "지역이 없습니다."),

    // 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_4001", "미션이 없습니다."),
    MISSION_CHALLENGING(HttpStatus.NOT_FOUND, "MISSION_4002", "미션이 진행중입니다.")
    ;



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    // enum 자신의 값으로 가지고 있던 message, code, httpStatus값을
    // interface의 메소드 오버라이딩을 통해 DTO를 만드는 것을 확인할 수 있었다.

    @Override
    // - 메서드 선언에만 달 수 있으며,
    // 상위 타입(부모 타입, BaseErrorCode)의 메서드를
    // 재정의했음을 알린다
    // 기능
    // - 자식 클래스에 여러 개의 메서드가 정의되어 있는 경우
    // 오버라이드 메서드를 사용하여 명시적으로 선언하면
    // 많은 메서드 중에서 어떠한 메서드가 부모 클래스로부터
    // 오버라이딩이 되었는지 쉽게 파악할 수 있다
    // - 컴파일러에게 문법 체크를 하도록 알린다
    // 오버라이딩하기 위해서는 부모 클래스에 있는 메서드명과 매개변수를
    // 동일하게 가져가야 하기 때문이다
    public ErrorReasonDTO getReason(){
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus(){
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
