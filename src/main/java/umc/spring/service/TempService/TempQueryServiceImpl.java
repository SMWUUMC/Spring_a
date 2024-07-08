package umc.spring.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;

@Service
// 로직 처리
// 서비스 레이어, 내부에서 자바 로직을 처리함
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {

    // 해당 Service에서는 에러를 만들어서 던지기만 했지,
    // 응답을 만드는 코드는 없다.
    @Override
    public void CheckFlag(Integer flag){
        if(flag == 1){
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
        // 에러 상황과 에러가 아닌 상황마다
        // 응답이 다른 것을 확인할 수 있다.
        // 에러를 만들어서 던지기만 했지,
        // 응답을 만드는 코드는 여기에 없다.
    }
    // 해당 코드에서 if 내부로 들어가게 되면
    // Service 이후 Controller로 가지 않고,
    // 바로 Exception handler에 의해 응답이 보내진다.

}
