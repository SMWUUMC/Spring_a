package umc.spring.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
// 그러면 TempHandler 클래스의 super에서
// GeneralException 클래스의 @AllArgsConstructor를 통해
// 만들어진 생성자가 호출된다.
public class GeneralException extends RuntimeException{
// GeneralException은 다시, RuntimeException을 상속받았기에,
// 이는 런타임에 발생한 Exception으로써
// MasterExceptionHandler가 감지하게 된다.
// ExceptionAdvice가 @RestControllerAdvice를 가지고 있기 때문이다.

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }

}
