package umc.spring.validation.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.CheckPage;

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

        modifyRequestParameter(pagenum - 1);

        return true;

    }

    private void modifyRequestParameter(int newPagenum){

        // 1. 현재 요청 객체의 속성을 가져오기
        // RequestContextHolder는 현재 요청의 속성을 ThreadLocal을 사용하여 저장하고 관리한다.
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            // 2. HttpServletRequest를 통해 객체 가져오기
            HttpServletRequest request = requestAttributes.getRequest();
            // 3. 페이지 번호 수정
            // 페이지 번호를 0 기반으로 변환한 값을 HttpServletRequest 객체의 속성으로 설정한다.
            // 이렇게 설정된 속성은 이후의 요청 처리 과정에서 사용될 수 있다.
            request.setAttribute("page", newPagenum);
        }

    }

    // int : 기본 타입
    // Integer : 참조 타입
    // 기본적으로 int 타입을 사용하는 것이 메모리 사용 측면에서 더 효율적입니다.
    // 메서드 내에서 간단한 계산이나 처리를 할 때는 int를 사용하는 것이 일반적입니다.
    // 이 경우에도 newPageValue는 단순히 계산 결과를 저장하고
    // 이를 HttpServletRequest의 속성으로 설정하는 용도이므로
    // int 타입을 사용하는 것이 더 적합합니다.

}
