package umc.mini.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.mini.domain.FoodCategory;
import umc.mini.domain.User;
import umc.mini.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserPrefer extends BaseEntity {

    // 유저 선호 음식 그룹 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 단방향 연관관계 (N:1, FK 설정)
    // 유저 id 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 음식 카테고리 id 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    // User 연관관계 설정
    public void setUser(User user){
        // 현재 UserPrefer 객체와 연관된 User 객체가 존재해서 이미 연관되어 있는 경우
        if(this.user != null)
            // 기존 User의 객체의 선호 목록에서 현재 UserPrefer 객체를 제거
            user.getUserPreferList().remove(this);
        // 새로운 User 객체와 연관 설정
        this.user = user;
        user.getUserPreferList().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory){
        this.foodCategory = foodCategory;
    }
}
