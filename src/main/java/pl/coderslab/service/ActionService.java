package pl.coderslab.service;

import pl.coderslab.entity.Action;

import java.util.List;

public interface ActionService {
    List<Action> findFirst25ByOrderByTimestampDesc();

    Action save(Action action);
}
