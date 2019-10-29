package notify.repositories;

import notify.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import notify.entities.File;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

    @Query("SELECT id FROM File f WHERE f.user = :user")
    List<String> findAllFileIdsOfUser(@Param("user") AppUser user);
}
