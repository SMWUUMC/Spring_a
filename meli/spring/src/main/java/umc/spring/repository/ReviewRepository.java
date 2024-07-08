package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    // 위의 코드는 Spring Data JPA에서
    // 메서드 이름만으로 SQL을 만들어주는 기능을 활용한 것입니다.
    // PageRequest는 페이징과 관련된 옵션이 포함됩니다.



    // 10주차 1. 내가 작성한 리뷰 목록 API
    Page<Review> findAllByMember(Member member, PageRequest pageRequest);

}
