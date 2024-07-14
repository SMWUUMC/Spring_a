package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Uuid;

// 12주차 사진도 같이 업로드하기
public interface UuidRepository extends JpaRepository<Uuid, Long> {



}
