package notify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import notify.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
