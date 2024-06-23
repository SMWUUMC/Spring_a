package umc.mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.mini.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
