package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
