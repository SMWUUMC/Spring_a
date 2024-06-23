package umc.mini.api.code.exception.handler;

import umc.mini.api.code.BaseErrorCode;
import umc.mini.api.code.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
