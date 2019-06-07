package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webshop.entities.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    @Query("SELECT id FROM Image")
    public List<String> findAllIds();
}
