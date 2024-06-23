package umc.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mini.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
