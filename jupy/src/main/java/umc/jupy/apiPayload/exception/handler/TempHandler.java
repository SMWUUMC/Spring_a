package umc.jupy.apiPayload.exception.handler;

import umc.jupy.apiPayload.code.BaseErrorCode;
import umc.jupy.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}