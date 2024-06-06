package umc.mini.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.mini.api.code.BaseCode;
import umc.mini.api.code.status.SuccessStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T>{

    // api 응답 형태
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;    // 1. 성공인지 아닌지 알려주는 필드
    private final String code;          // 2. HTTP 상태코드에서 조금 더 세부적인 응답 상황을 알려주기 위한 필드
    private final String message;       // 3. code에 대해서 추가적으로 우리에게 익숙한 문자로 상황을 알려주는 필드
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;                   // 4.실제로 클라이언트에게 필요한 데이터가 담기는 필드

    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode() , SuccessStatus._OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result){
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(), result);
    }


    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(true, code, message, data);
    }
}
