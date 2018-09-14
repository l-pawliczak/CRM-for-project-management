package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Action;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Integer> {
    List<Action> findFirst25ByOrderByTimestampDesc();
}
