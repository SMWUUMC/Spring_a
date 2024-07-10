package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.validation.annotation.ExistMemberMission;

import java.util.Optional;

// 10주차 4. 진행중인 미션 진행 완료로 바꾸기 API
@Component
@RequiredArgsConstructor
public class MemberMissionExistValidator implements ConstraintValidator<ExistMemberMission, Long> {

    private final MemberMissionQueryService memberMissionQueryService;

    @Override
    public void initialize(ExistMemberMission constraintAnnotation){

        ConstraintValidator.super.initialize(constraintAnnotation);

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context){

        Optional<MemberMission> target = memberMissionQueryService.findMemberMission(value);

        if(target.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBERMISSION_NOT_FOUND.toString()).addConstraintViolation();

            return false;
        }

        return true;

    }

}
