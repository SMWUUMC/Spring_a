package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.ChallengingMission;

// 9주차 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
// - MemberMission을 추가하는 것이 목적
@Component
@RequiredArgsConstructor
//public class MissionChallengingValidator implements ConstraintValidator<ChallengingMission, List<Long>> {
public class MissionChallengingValidator implements ConstraintValidator<ChallengingMission, Long> {

    //private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(ChallengingMission constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
//    public boolean isValid(List<Long> values, ConstraintValidatorContext context){
//        boolean isValid = values.stream()
//                .allMatch(value -> missionRepository.existsById(value));
//
//        if(!isValid){
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString()).addConstraintViolation();
//        }
//
//        return isValid;
//
//    }
    public boolean isValid(Long missionId, ConstraintValidatorContext context){
        boolean isChallenging = memberMissionRepository.existsByMissionIdAndStatus(missionId, MissionStatus.CHALLENGING);

        if(isChallenging){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_CHALLENGING.toString()).addConstraintViolation();
        }

        return !isChallenging;

    }

}
