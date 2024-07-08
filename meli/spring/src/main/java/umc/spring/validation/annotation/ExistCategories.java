package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented
// 사용자 정의 어노테이션을 만들 때 붙입니다.
// - 해당 어노테이션을 javadoc에 포함시킨다.
// - @Documented를 단 함수는 해당 어노테이션까지 표기된다.
// - 반대로 @Documented가 없는 함수는 해당 어노테이션 표기가 안된다.
// - 이와 같이 @Documented는 javadoc에 해당 어노테이션을
// javadoc에 표기할지에 대한 여부를 지정하는 어노테이션이다.
@Constraint(validatedBy = CategoriesExistValidator.class)
// java에서 제공하는 사용자가 validation을
// 커스텀 어노테이션을 통해 할 수 있도록 제공하는 어노테이션입니다.
// @Constraint의 파라미터로 validatedBy가 있고
// CategoriesExistValidator.class를 지정하고 있죠.
// 이로써 CategoriesExistValidator라는 클래스를 통해
// @ExistCategories가 붙은 대상을 검증합니다.
/*
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {
 */
// - 사용자가 원하는 Constraint와 Validation을 만들어 적용할 수 있다.
// - Enum에 있는 타입이 존재하지 않는 경우 에러가 발생하는데,
// 이러한 에러 처리 과정들을 Controller 코드에 추가할 경우,
// 코드가 지저분해지고 에러 처리 과정의 일관성이 떨어지게 된다.
// - 이를 해결하기 위해
// 커스텀 Constraint(CategoriesExistValidator)를 만들고
// 데이터 벨리데이션 과정을 Controller에서 분리시켜 처리하도록 한다.
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
// 어노테이션의 적용 범위를 지정합니다.
// - 사용자가 만든 annotation이 부착될 수 있는 타입을 지정한다.
@Retention(RetentionPolicy.RUNTIME)
// 어노테이션의 생명 주기를 지정합니다.
// 위의 코드는 RUNTIME이기에 실행 하는 동안에만 유효하게 됩니다.
// - 어노테이션의 라이프 사이클,
// 즉 어노테이션이 언제까지 살아 남아 있을지를 정하는 것이다.
// - RetentionPolicy.SOURCE : 소스 코드(.java)까지 남아있는다.
// - RetentionPolicy.CLASS : 클래스 파일(.class)까지 남아있는다.(=바이트 코드)
// - RetentionPolicy.RUNTIME : 런타임까지 남아있는다.(=사실상 안 사라진다.)
public @interface ExistCategories {
// 커스텀 어노테이션을 만들어 어노테이션을 정의한다.
// @interface는 자동으로 어노테이션 클래스를 상속(확장)하며,
// 내부의 메소드들은 abstract 키워드가 자동으로 붙게 된다.
// 따라서 어노테이션 인터페이스는 extends절을 가질 수 없으며,
// 추가적으로 다음과 같은 제약이 존재한다.
// - 어노테이션 타입 선언은 제네릭일 수 없다.
// - 메소드는 매개변수를 가질 수 없다.
// - 메소드는 타입 매개변수를 가질 수 없다.
// - 메소드 선언은 throws 절을 가질 수 없다.

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    // <?> : 와일드 카드 (Wild card) (= <? extends Object>)
    // Object는 자바에서의 모든 API 및 사용자 클래스의 최상위 타입이다.
    // 한마디로 <?>는 어떤 타입이든 상관 없다는 의미이다.
    // 이는 보통 데이터가 아닌 '기능'의 사용에만 관심이 있는 경우에
    // <?>로 사용할 수 있다.
    // 제네릭을 특정 범위 내로 좁혀서 제한하고 싶을 때 사용한다.
    // ?는 와일드 카드라고 해서 쉽게 말해 '알 수 없는 타입'이라는 의미이다.
    Class<? extends Payload>[] payload() default {};
    // <? extends T> : 상한 경계
    // 유형 경계를 지정하지만 타입이 지정되지는 않는다.

}
