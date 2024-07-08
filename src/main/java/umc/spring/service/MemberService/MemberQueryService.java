package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);



    // 10주차 1. 내가 작성한 리뷰 목록 API
    Page<Review> getReviewList(Long memberId, Integer page);
    // 위의 Page는 Spring Data JPA에서 제공하는
    // Paging을 위한 추상화를 제공합니다.
    // Page 자체에 페이징과 관련된 여러 정보가 담기게 되며
    // 위에서 작성한 DTO에서 그 흔적을 찾아볼 수 있습니다.

    // 10주차 3. 내가 진행중인 미션 목록 API
    Page<MemberMission> getMemberMissionChallengingList(Long memberId, Integer page);

}
