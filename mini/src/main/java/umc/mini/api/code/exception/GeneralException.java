package umc.mini.api.code.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.mini.api.code.BaseErrorCode;
import umc.mini.api.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}