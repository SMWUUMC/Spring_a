package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

// 12주차 사진도 같이 업로드하기

// 우선 너무 코드가 복잡해지지 않도록 하기 위해
// 리뷰에 사진이 여러 장 가능하지만 한 장만 업로드를 해보겠습니다.
// 우선 사진 각각을 아마존 자체에서도 구분이 가능해야 합니다.
// 그 이유는 물론 사진마다 이름이 존재하겠지만…
// 사람이 붙이는 이름이 같을 수 있기 때문에
// 따로 겹치지 않는 정보가 필요합니다.
// 가장 쉬운 방법은 업로드 하는 파일에
// 일련번호를 붙이는 방법이 있겠지만.. 이 방법도 유효하지 않습니다.
// 왜냐하면 자바 자체적으로 변수를 두면
// 서버가 꺼질 때 초기화가 되기 때문에 영속되는 데이터로 둬야 합니다.
// 저희는 UUID라는 것을 사용해서 일련번호를 두겠습니다.
// 4UUID는 겹칠 확률이 극히 적은(사실상 안 겹칩니다)
// 일련의 식별자 입니다!
// Java에서는 UUID를 생성해주는 API가 존재하며
// 이를 이용해서 UUID를 담은 엔티티를 하나 만들어서 사용합시다.
// 이후 AWS S3에 업로드 시 uuid를 파일 이름에 붙여서
// 업로드 해서 업로더가 지은 파일의 이름이
// 동일하더라도 각각의 파일이 식별이 되도록 합시다!
@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Uuid extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

}
