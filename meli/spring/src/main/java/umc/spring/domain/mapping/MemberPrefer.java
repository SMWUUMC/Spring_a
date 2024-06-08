package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {
// member_prefer 테이블의 경우 member와 food_category 모두에 대한 외래키를 가지고 있다
// 1:N의 경우
// - N에 해당하는 테이블이 외래키를 가지며 N에 해당하는 엔티티가 연관 관계의 주인이다
// 1:1의 경우
// - 둘 중 하나만 외래키를 가지면 되기에 원하는 엔티티를 연관 관계 주인으로 설정한다

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    // N:1에서 N에 해당하는 엔티티(MemberPrefer)가
    // 1에 해당하는 엔티티(Member)와 연관 관계를 매핑할 때
    // @ManyToOne 어노테이션을 쓴다
    // (fetch = FetchType.LAZY)는 지연 로딩을 설정하는 것이다
    // FetchType
    // - JPA가 하나의 Entity를 조회할 때,
    // 연관관계에 있는 객체들을 어떻게 가져올 것인가를 나타내는 설정값이다
    // - JPA는 ORM 기술로, 사용자가 직접 쿼리를 생성하지 않고,
    // JPA에서 JPQL을 이용하여 쿼리문을 생성하기 때문에
    // 객체와 필드를 보고 쿼리를 생성한다
    // - 따라서, 다른 객체와 연관관계 매핑이 되어있으면 그 객체들까지 조회하게 되는데,
    // 이때 이 객체를 어떻게 불러올 것인가를 설정할 수 있다
    // fetch의 디폴트 값
    // - @xxToOne에서는 EAGER
    // - @xxToMany에서는 LAZY
    // 즉시로딩(EAGER)
    // - 데이터를 조회할 때, 연관된 모든 객체의 데이터까지 한 번에 불러오는 것
    // 지연로딩(LAZY)
    // - 필요한 시점에 연관된 객체의 데이터를 불러오는 것
    // - 가급적이면 기본적으로 지연로딩을 사용하는 것이 좋다
    @JoinColumn(name = "member_id")
    // 실제 데이터베이스에서 해당 칼럼(외래키)의 이름을 설정하는 것
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    // 연관 관계 매핑은 단방향 매핑과 양방향 매핑이 있다
    // 연관 관계 주인
    // - 실제 데이터베이스에서 외래키를 가지는 엔티티(테이블)
    // 단방향 매핑
    // - 연관 관계 주인에게만 연관 관계를 주입하는 것

    public void setMember(Member member){
        if(this.member != null){
            member.getMemberPreferList().remove(this);
        }

        this.member = member;

        member.getMemberPreferList().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory){
        this.foodCategory = foodCategory;
    }

}
