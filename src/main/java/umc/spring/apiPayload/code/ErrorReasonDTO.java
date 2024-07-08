package umc.spring.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
// - Lombok 플러그인이 지원해주는 어노테이션
// - 클래스를 생성할 수 있다.
// - 객체를 생성하는 방법 중 하나
// 빌더 패턴
// - 정보들은 자바빈즈패턴처럼 받되,
// 데이터 일관성을 위해 정보들을 다 받은 후에 객체를 생성한다.
// 빌더를 써야하는 이유
// 1. 생성자 파라미터가 많을 경우 가독성이 좋지 않다.
// 2. 어떤 값을 먼저 설정하던 상관 없다.
public class ErrorReasonDTO {

    private HttpStatus httpStatus;

    private final boolean isSuccess;
    private final String code;
    private final String message;

    public boolean getIsSuccess(){
        return isSuccess;
    }

}
