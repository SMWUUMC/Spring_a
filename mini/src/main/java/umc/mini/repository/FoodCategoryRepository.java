package umc.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mini.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
