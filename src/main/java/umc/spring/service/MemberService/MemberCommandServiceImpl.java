package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
// Member 객체를 만드는 작업 (클라이언트가 준 DTO to Entity)를
// 서비스 단에서 할까요?
// 7주차에서 설명했지만, 프로젝트마다 다릅니다.
// 저의 경우 서비스는 오로지 비즈니스 로직에만 집중을 합니다.
// 따라서 Member를 만드는 작업을 converter에서 하겠습니다.
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    // @Transactional을 통해 트랜잭션을 설정해줍니다.
    // 선언적 트랜잭션
    // - 트랜잭션에 관한 행위를 정의하는 것
    // 사용 방법
    // - @Transactional을 클래스 단위
    // 혹은 메서드 단위에 선언해주면 된다.
    // - 클래스에 선언하게 되면,
    // 해당 클래스에 속하는 메서드에 공통적으로 적용된다.
    // - 메서드에 선언하게 되면, 해당 메서드에만 적용된다.
    // 동작 원리
    // - 트랜잭션은 Spring AOP를 통해 구현되어있다.
    // - 더 정확하게 말하면, 어노테이션 기반 AOP를 통해 구현되어있다.
    // 따라서, 아래와 같은 특징이 있다.
    // - 클래스, 메소드에 @Transactional이 선언되면 해당 클래스에
    // 트랜잭션이 적용된 프록시 객체 생성
    // - 프록시 객체는 @Transactional이 포함된 메서드가 호출될 경우,
    // 트랜잭션을 시작하고 Commit or Rollback을 수행
    // - CheckedException or 예외가 없을 때는 Commit
    // - UncheckedException이 발생하면 Rollback
    // 주의점
    // 1. 우선순위
    // - @Transactional은 우선순위를 가지고 있다.
    // - 우선순위 :
    // 클래스 메소드 > 클래스 > 인터페이스 메소드 > 인터페이스
    // - 따라서 공통적인 트랜잭션 규칙은 클래스에,
    // 특별한 규칙은 메서드에 선언하는 식으로 구성할 수 있다.
    // 또한, 인터페이스 보다는 클래스에 적용하는 것을 권고한다.
    // - 인터페이스나 인터페이스의 메서드에 적용할 수 있다.
    // - 하지만, 인터페이스 기반 프록시에서만 유효한 트랜잭션 설정이 된다.
    // - 자바 어노테이션은 인터페이스로부터 상속되지 않기 때문에
    // 클래스 기반 프록시 or AspectJ 기반에서
    // 트랜잭션 설정을 인식할 수 없다.
    // 2. 트랜잭션의 모드
    // - @Transactional은 Proxy Mode와 AspectJ Mode가 있는데
    // Proxy Mode가 Default로 설정되어있다.
    // Proxy Mode는 다음과 같은 경우 동작하지 않는다.
    // - 반드시 public 메서드에 적용되어야한다.
    // - Protected, Private Method에서는 선언되어도
    // 에러가 발생하지는 않지만, 동작하지도 않는다.
    // - Non-Public 메서드에 적용하고 싶으면
    // AspectJ Mode를 고려해야한다.
    // - @Transactional이 적용되지 않은 Public Method에서
    // @Transactional이 적용된 Public Method를 호출할 경우,
    // 트랜잭션이 동작하지 않는다.
    public Member joinMember(MemberRequestDTO.JoinDTO request){

        Member newMember = MemberConverter.toMember(request);

        // FoodCategory의 리스트를 얻기
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
        // for문 보다는 stream을 사용 할 것을 강력히 권고합니다.
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        // 단방향 연관 관계 설정
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        // 양방향 연관 관계 설정
        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);

    }

}
