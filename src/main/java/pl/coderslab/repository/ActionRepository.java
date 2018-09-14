package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Action;

public interface ActionRepository extends JpaRepository<Action, Integer> {
}
