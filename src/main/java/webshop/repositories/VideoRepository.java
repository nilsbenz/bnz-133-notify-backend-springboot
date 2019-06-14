package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.entities.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
