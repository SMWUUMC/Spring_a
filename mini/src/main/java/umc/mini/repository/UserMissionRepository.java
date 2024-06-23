package umc.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mini.domain.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}
