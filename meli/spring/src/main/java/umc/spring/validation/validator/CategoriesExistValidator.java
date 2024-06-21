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
// 우선 ConstraintValidator 인터페이스에 대한
// 구체화 클래스로서 만들어야 합니다.
// 이때, ExistCategories 어노테이션에 대한 로직을 담을 것이며
// 검증 대상이 List<Long>임을 명시하죠.
// 인터페이스를 구현하려면 메소드를 오버라이딩 해야합니다.
// implement methods를 클릭 후,
// 어떤 메소드를 오버라이딩 할 것인지 물어보는데,
// 그냥 전부 다 선택 후 확인을 해주세요.
// isValid 메소드만 우리가 원하는 형태로 바꾸면 됩니다
// ConstraintValidator는 isValid 메소드의 반환 값을 확인하여
// 검증이 되었는지 실패했는 지를 알려줍니다.
// 만약 isValid의 리턴 값이 false면
// ConstraintViolationException을 발생시킵니다.
// 그런데 MemberRequestDTO.JoinDTO를 가보면
// @ExistCategories 어노테이션이 Request Body로 받아 올
// DTO(MemberRequestDTO.JoinDTO)의
// preferCategory필드에 붙어있으므로
// MemberRestController 컨트롤러에서
// RequestBody를 받아오는 과정에서
// @ExistCategories가 붙어있는
// MemberRequestDTO.JoinDTO.preferCategory로 인해
// CategoriesExistValidator.isValid의
// ConstraintValidatorContext 코드가 실행된다.
// 이제 isValid 메소드에서 false를 리턴하면
// ConstraintViolationException을 발생시키는데,
// MemberRestController.join에서 @Valid 어노테이션이 존재하므로
// @ExistCategories에서 발생한 예외가 바로 전달되지 않고,
// @Valid 어노테이션이
// MethodArgumentNotValidException을 발생시킵니다.
// 따라서 최종적으로 ExceptionAdvice의
// handleMethodArgumentNotValid에서
// MethodArgumentNotValidException를 감지하게 됩니다.
// 정리
// 1. MemberRestController에서 클라이언트가 넘겨준 HTTP RequestBody의 데이터를 MemberRequestDTO로 변환함
// 2. 그 과정에서 MemberRequestDTO에 있는 preferCategory의 @ExistCategories가 동작
// 3. CategoriesExistValidator에서 isValid의 false 값을 확인하고 `ConstraintViolationException` 를 발생
// 4. @Valid가 있으므로 `ConstraintViolationException` 가 곧바로 전달되지 않음
// 5. @Valid입장에서 역시나 검증이 실패 한 것을 감지하여 `MethodArgumentNotValidException` 생성
// 6. 최종적으로 ExceptionAdvice의 handleMethodArgumentNotValid가 감지 후 응답 생성
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

    // repository에 접근하는 계층은 무조건 service 하나만 있어야 함
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public void initialize(ExistCategories constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context){
    // isValid함수에서 검증 대상인 List<Long> 의 값을 가진
    // 카테고리가 모두 데이터베이스에 있는 지를 판단하고
    // 없을 경우 false를 반환합니다.

        boolean isValid = values.stream()
                .allMatch(value -> foodCategoryRepository.existsById(value));

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }

}
