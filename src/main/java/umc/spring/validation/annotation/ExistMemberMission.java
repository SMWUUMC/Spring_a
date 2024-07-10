package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MemberMissionExistValidator;

import java.lang.annotation.*;

// 10주차 4. 진행중인 미션 진행 완료로 바꾸기 API
@Documented
@Constraint(validatedBy = MemberMissionExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMemberMission {

    String message() default "해당 멤버미션이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
