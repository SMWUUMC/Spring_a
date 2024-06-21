package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

// Exception 핸들링 하기
// GET /temp/exception에 대해 QueryString에 flag를 받아오는데,
// 해당 flag가 2인 경우 exception을 만드는 경우를 테스트해본다.
public class TempHandler extends GeneralException {
// TempQueryServiceImpl에서 해당 핸들러를 만들면
// GeneralException을 만든다.

    public TempHandler(BaseErrorCode errorCode){
        super(errorCode);
        // super는 부모 클래스의 생성자를 호출하는 것이다.
    }
    // 그러면 GeneralException 클래스에서 @AllArgsConstructor를 통해
    // 만들어진 생성자가 호출된다.

}
