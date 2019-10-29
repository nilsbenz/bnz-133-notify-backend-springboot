package notify.repositories;

import notify.entities.AppUser;
import notify.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {

    @Query("SELECT n FROM Note n WHERE n.user = :user")
    List<Note> findAllOfUser(@Param("user") AppUser user);
}
