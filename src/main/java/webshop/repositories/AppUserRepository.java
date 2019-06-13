package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
