package umc.mini.api.code.exception.handler;

import umc.mini.api.code.BaseErrorCode;
import umc.mini.api.code.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
