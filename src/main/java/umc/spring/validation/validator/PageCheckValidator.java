package umc.spring.validation.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.CheckPage;

@Component
@RequiredArgsConstructor
public class PageCheckValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer pagenum, ConstraintValidatorContext context){

        if(pagenum <= 0){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_FOUND.toString()).addConstraintViolation();

            return false;
        }

        return true;

    }



    // int : 기본 타입
    // Integer : 참조 타입
    // 기본적으로 int 타입을 사용하는 것이 메모리 사용 측면에서 더 효율적입니다.
    // 메서드 내에서 간단한 계산이나 처리를 할 때는 int를 사용하는 것이 일반적입니다.
    // 이 경우에도 newPageValue는 단순히 계산 결과를 저장하고
    // 이를 HttpServletRequest의 속성으로 설정하는 용도이므로
    // int 타입을 사용하는 것이 더 적합합니다.

}
