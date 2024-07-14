package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.ReviewImage;

// 12주차 사진도 같이 업로드하기
public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {



}
