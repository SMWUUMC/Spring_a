package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.mapping.MemberPrefer;

import java.util.List;
import java.util.stream.Collectors;

// FoodCategory의 리스트를 얻었으니 이를 이용해
// MemberPrefer 엔티티를 만들어 줍시다.
// 양방향 연관 관계 설정(member)은 converter 보다는
// service에서 하는 것이 좋습니다!
// 단방향(foodCategory)은 컨버터에서 설정을 해도 괜찮습니다.
public class MemberPreferConverter {

    // 단방향 연관 관계 설정
    // 따라서 MemberPrefer 생성 시 member를 넣지 않았습니다.
    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());

    }

}
