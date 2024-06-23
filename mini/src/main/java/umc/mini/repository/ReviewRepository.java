package umc.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mini.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
