package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

@Component
// @Component 어노테이션을 이용하면
// Bean Configuration 파일에 Bean을 따로 등록하지 않아도
// 사용할 수 있다.
// 빈 등록 자체를 빈 클래스 자체에다가 할 수 있다는 의미이다.
// @Component 어노테이션은 기본적으로 타입기반의
// 자동주입 어노테이션이다.
// @Autowired, @Resource와 비슷한 기능을 수행한다고 할 수 있겠다.
// IoC 컨테이셔 객체를 생성할 때 자동으로 객체가 생성되며,
// Singleton이다.
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {
/*
@Constraint(validatedBy = CategoriesExistValidator.class)
 */
// constraintValidator와 custom annotation을 구현하면
// 아예 Controller로 데이터가 들어오기 전인
// Interceptor 단계에서 값을 체크하고 예외 처리를 할 수 있다.
// ConstraintValidator는 값의 유효성을 체크하는
// 어노테이션을 제공하는 'javax.validation'에서 제공하는
// 인터페이스이다.
// ConstraintValidator는 아래와 같이 Implements해서 사용할 수 있다.
// ConstraintValidator<${유효성 검사를 할 어노테이션}, ${유효성 검사를 할 Class}>
// 장점
// 1. 일관성있는 처리 방법
// 검증방법과 검증시점에 대해 통일성을 가질 수 있다.
// 이에 대해 부연 설명을 하자면...
// 사람마다 검증하는 단계가 컨트롤러가 될 수도 있고
// 서비스 레이어가 될수도 있는데
// 이를 validation을 사용함으로써 controller 진입 전
// interceptor 단계에서 검증을 함으로써
// 일관성을 가질수 있다는 의미이다.
// 2. 일관성 있는 ErrorResponse
// 에러가 발생하면 ConstraintViolationException이 발생한다.
// 이를 통해 에러처리의 응답을 일관성있게 리턴할 수 있다.

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public void initialize(ExistCategories constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context){

        boolean isValid = values.stream()
                .allMatch(value -> foodCategoryRepository.existsById(value));

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }

}
