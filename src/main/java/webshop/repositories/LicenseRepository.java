package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webshop.entities.AppUser;
import webshop.entities.Image;
import webshop.entities.License;

import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License, String> {

    @Query("SELECT l FROM License l WHERE l.image = :image AND l.user = :user")
    public Optional<License> getLicense(@Param("image") Image image, @Param("user") AppUser user);
}
