package umc.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mini.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
