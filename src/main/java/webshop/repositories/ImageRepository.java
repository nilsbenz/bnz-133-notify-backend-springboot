package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
