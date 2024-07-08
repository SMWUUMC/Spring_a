package umc.spring.apiPayload.code;

// 먼저 두 개의 인터페이스인 BaseCode와 BaseErrorCode의 역할은
// 이를 구체화 하는 Status에서
// 두 개의 메소드를 반드시 Override할 것을 강제한다.
public interface BaseErrorCode {
// API 응답에 들어갈 message의 형식

    // ErrorStatus에서 @Override 되었다.
    public ErrorReasonDTO getReason();

    // ErrorStatus에서 @Override 되었다.
    public ErrorReasonDTO getReasonHttpStatus();

}
