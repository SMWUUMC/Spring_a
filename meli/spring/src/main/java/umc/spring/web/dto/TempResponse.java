package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// RequestBody에 담겨오는 값은 없으므로, TempResponse만 작성한다.
public class TempResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    // - 파라미터가 없는 디폴트 생성자를 생성
    /*
    public class Person {
        private String name;
        private int age;

        public Person(){}
    }
     */
    @AllArgsConstructor
    public static class TempTestDTO{
    // public static class
    // - DTO들은 저렇게 큰 묶음으로 (멤버 관련 DTO 등등...) 클래스를 만들고,
    // 내부적으로 static 클래스를 만드는 것이 좋다.
    // - DTO 자체는 수많은 곳에서 사용될 수 있기에
    // static class로 만들게 되면,
    // 매번 class 파일을 만들 필요도 없고,
    // 범용적으로 DTO를 사용할 수 있다.
        String testString;
    }
    // DTO에도 빌더 패턴을 사용한다
    // - 그냥 우리가 만드는 인스턴스들은 모두 빌더 패턴을 사용한다고 생각하면 된다.
    // - 참고로, RequestDTO는 우리(서버)가 만드는 것이 아닌,
    // 프론트엔드에서 만든 객체를 그저 받기에
    // RequestDTO는 빌더 패턴을 적용할 필요가 없다.
    // - 우리(서버)는 그저 받기만 하면 되기 때문이다.

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempExceptionDTO{
        Integer flag;
    }

}
