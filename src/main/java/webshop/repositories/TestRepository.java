package webshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop.entities.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
}
