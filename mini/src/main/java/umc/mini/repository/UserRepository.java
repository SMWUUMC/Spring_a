package umc.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mini.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
