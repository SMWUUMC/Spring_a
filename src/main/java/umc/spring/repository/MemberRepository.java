package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;

// Spring Data JPA를 사용할 것이기에 인터페이스로 만들어줍니다.
public interface MemberRepository extends JpaRepository<Member, Long> {

}
