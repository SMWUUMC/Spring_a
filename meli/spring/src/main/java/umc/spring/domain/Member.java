package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MemberStatus;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.mapping.MemberAgree;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 어노테이션
@Entity
// 해당 클래스가 JPA의 엔티티임을 명시
@Getter
// lombok에서 제공
// getter를 만들어주는 어노테이션
@Builder
// 빌더 클래스와 이를 반환하는 builder() 메서드 생성
// Lombok의 @Builder
// 클래스에 @Builder 어노테이션만 붙여주면 클래스를 컴파일 할 때
// 자동으로 클래스 내부에 빌더 API가 만들어진다.
// 단, 롬복의 @Builder는 GOF의 디렉터 빌더가 아닌 심플 빌더 패턴을 다룬다
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
// @Builder 어노테이션을 선언하면 전체 인자를 갖는 생성자를 자동으로 만든다

// 위 세 개의 어노테이션(@Builder, @NoArgsConstructor, @AllArgsConstructor)은
// 자바의 디자인 패턴 중 하나인 빌더 패턴을 사용하기 위함이다
// 빌더 패턴을 사용하면 생성자를 사용하는 것보다 더욱 편리하게 코딩이 가능하다
// 어떤 문제를 해결하기 위해 빌더 패턴을 사용하는가?
// - 크게 "일관성 문제" & "불변성 문제" 때문이다.
// 1. 불변성 문제
// 클라이언트 프로그램으로부터 팩토리 클래스로 많은 파라미터를 넘겨줄 때
// 타입, 순서 등에 대한 관리가 어려워져 에러가 발생할 확률이 높아진다.
// 2. 일관성 문제
// 경우에 따라 필요 없는 파라미터들에 대해서 팩토리 클래스에 일일이
// null 값을 넘겨줘야 한다.
// 3. 일관성 문제
// 생성해야 하는 sub class가 무거워지고 복잡해짐에 따라
// 팩토리 클래스 또한 복잡해진다.
// - 빌더 패턴은 이러한 문제들을 해결하기 위해 별도의 Builder 클래스를 만들어
// 필수 값에 대해서는 생성자를 통해, 선택적인 값들에 대해서는 메소드를 통해
// step-by-step으로 값을 입력받은 후에 build() 메소드를 통해
// 최종적으로 하나의 인스턴스를 리턴하는 방식이다.
// 빌더 패턴
// - 복잡한 객체를 생성하는 방법을 정의하는 클래스와 표현하는 방법을 정의하는 클래스를 별도로 분리하여,
// 서로 다른 표현이라도 이를 생성할 수 있는 동일한 절차를 제공하는 패턴
// - 복잡한 객체의 생성 과정과 표현 방법을 분리하여 다양한 구성의 인스턴스를 만드는 생성 패턴
// - 빌더 패턴은 생성 패턴(Creational Pattern) 중 하나이다
// - 많은 Optional한 멤버 변수(혹은 파라미터)나 지속성 없는 상태 값들에 대해 처리해야 하는 문제들을 해결한다.
// 생성 패턴
// - 인스턴스를 만드는 절차를 추상화하는 패턴
// 생성 패턴에서 중요한 이슈
// 1. 생성 패턴은 시스템이 어떤 Concrete Class를 사용하는지에 대한 정보를 캡슐화한다.
// 2. 생성 패턴은 이들 클래스의 인스턴스들이 어떻게 만들고 어떻게 결합하는지에 대한 부분을 완전히 가려준다.
// - 쉬운 말로 정리하자면, 생성 패턴을 이용하면 무엇이 생성되고,
// 누가 이것을 생성하며, 이것이 어떻게 생성되는지,
// 언제 생성할 것인지 결정하는 데 유연성을 확보할 수 있게 된다.
// 빌더 패턴(Builder Pattern)의 장점
// 1. 필요한 데이터만 설정할 수 있음
// - 필수 멤버와 선택적 멤버를 분리 가능
// 2. 유연성을 확보할 수 있음
// - 디폴트 매개변수 생략을 간접적으로 지원
// 3. 가독성을 높일 수 있음
// - 객체 생성 과정을 일관된 프로세스로 표현
// - 초기화 검증을 멤버별로 분리
// 4. 변경 가능성을 최소화할 수 있음
// - 객체 생성 단계를 지연할 수 있음
// - 멤버에 대한 변경 가능성 최소화를 추구
// 빌더 패턴 단점
// 1. 코드 복잡성 증가
// 2. 생성자보다는 성능은 떨어진다
// 3. 지나친 빌더 남용은 금지
// 빌더 패턴 네이밍 형식
// 1. 멤버이름()
// 2. set멤버이름()
// 3. with멤버이름()
// 빌더 디자인 패턴 종류
// 1. 심플 빌더 패턴 (Effective Java, 2001 발매)
// - 생성시 지정해야 할 인자가 많을때 사용
// - 객체의 일관성 불변성이 목적
// - 생성자가 많을 경우 또는 변경 불가능한 불변 객체가 필요한 경우
// 코드의 가독성과 일관성, 불변성을 유지하는 것에 중점을 둔다
// - Lombok의 @Builder가 여기에 해당한다
// 2. GoF의 빌더 패턴 (1994 발매)
// - 객체의 생성 단계 순서를 결정해두고 각 단계를 다양하게 구현하고 싶을때 사용
// - 복잡한 객체의 생성 알고리즘과 조립 방법을 분리하여 빌드 공정을 구축하는 것이 목적
// - GOF의 빌더 패턴은 여러 디자인 패턴의 짬뽕

//public class Member {
public class Member extends BaseEntity {
// extends로 BaseEntity를 상속받는다

    // 기본 키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // strategy = GenerationType.IDENTITY
    // - JPA가 통신을 하는 DBMS의 방식을 따른다는 뜻
    // - 우리는 MySQL을 사용하니, MySQL을 따르게 된다.
    private Long id;

    @Column(nullable = false, length = 20)
    // JPA에서 칼럼에 대한 세부적인 설정을 한다
    private String name;
    // Member에서 name이 table에서는 varchar(20)인데,
    // JPA에서 String으로 되어있으므로
    // 각 칼럼 별로 세부적인 설정(unique, default, nullable 등)을
    // 해주어야 한다

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;
    // 테이블에서는 spec_address
    // 순수 JPA와 달리 Spring data JPA를 사용하면,
    // 실제 DB에 적용 시 specAddress를 spec_address로 바꿔준다

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    // columnDefinition으로 칼럼의 타입을 직접 지정할 수도 있다
    // gender와 status는 enum으로 되어있어 columnDefinition으로 설정한다
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    // 'ACTIVE'가 아닌 ACTIVE로 하면 오류가 나서 터진다
    // MySQL은 문자열을 무조건 ' '로 감싸야 한다
    // 칼럼의 default 값은 @ColumnDefault('ACTIVE')로도 가능하다
    // 역시나 문자열은 ' '로 감싸야 터지지 않는다
    private MemberStatus status;
    // @Enumerated 어노테이션을 이용해서 enum을 entity에 적용할 수 있다.
    // 이 때, 반드시 EnumType을 STRING으로 한다.
    // 기본 값인 ORDINAL을 사용하면 데이터베이스에 enum의 순서가 저장이 되는데,
    // 만약 Springboot에서 enum의 순서를 바꾸게 될 경우 에러가 생긴다.
    // 따라서 반드시 STRING을 사용한다.

    private LocalDate inactiveDate;

    @Column(nullable = false, length = 50)
    private String email;

    private Integer point;

    // 양방향 매핑을 사용하게 될 경우 버그가 생길 위험이 있지만,
    // 양방향 매칭으로 인한 객체 그래프 탐색은 프로그래밍을 굉장히 편리하게 해준다
    // 양방향 매핑
    // - 연관 관계 주인이 아닌 엔티티에게도 연관 관계를 주입하는 것
    // - 1:N에서 1에 해당하는 엔티티에게 양방향 매핑을 설정한다
    // 양방향 매핑의 장점
    // - 객체 그래프 탐색으로 인한 이점이 있다
    // - 필수적인 기능인 cascade의 설정이 가능하다
    // database에서의 cascade
    // - 본래 외래키를 가진, 연관 관계의 주인인 테이블에 설정을 해서
    // - 참소 대상인 테이블의 칼럼이 삭제 될 때 같이 삭제되거나 변경이 될 때
    // 같이 변경이 되는 기능이다
    // JPA에서의 cascade
    // - JPA에서는 반대로 연관관계의 주인이 아닌,
    // 참조의 대상이 되는 엔티티에 설정을 해야한다
    // - 이는, 단방향 매핑 만으로는 cascade 설정을 하는 것이 문제가 있다는 것이다
    // - 만약 단방향 매핑만 적용이 된 상태에서 cascade를 설정 시,
    // member를 참조하는 review가 삭제될 때 member도 같이 삭제되는 끔찍한 상황이 발생한다
    // - 따라서 양방향 매핑이 있어야 올바르게 cascade의 적용이 가능하다
    // - 단, 양방향 매핑을 할 경우 연관 관계 편의 메서드가 필요하게 된다
    // 연관 관계 편의 메서드
    // - 양방향 연관관계의 양쪽 모두의 관계를 맺어주는 것을 하나의 코드처럼 사용하도록 설정하는 메서드
    // - 한 번에 양방향 관계를 설정하는 메서드

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    // 양방향 매핑의 경우 1:N에서 1에 해당하는 엔티티에게 설정한다
    // @OneToMany
    // - 1에 해당하는 엔티티가 N에 해당하는 엔티티와 관계가 있음을 명시한다
    // - 이 때, N에 해당하는 엔티티에서 ManyToOne이 설정된 member 변수를 mappedBy 한다
    // CascadeType.ALL
    // - Member의 변화에 따라 Review, MemberPrefer 등의 엔티티가 영향을 받는다는 것을 의미한다
    // - 이렇게 하면, Member가 삭제될 때, 멤버를 참조하는 나머지 데이터도 같이 삭제가 된다
    // - 만약 이렇게 하지 않으면, 나중에 멤버 삭제 시,
    // 연결된 데이터들을 하나 하나 다 삭제를 해야 해서 매우 짜증난다
    // - 그리고 member가 적은 게시글을 member가 탈퇴 시 삭제를 할지 말지도
    // 기획자와 논의를 해서 결정해야한다
    // - 에브리타임만 봐도 탈퇴한다고 게시글이 사라지지 않는다
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

}
