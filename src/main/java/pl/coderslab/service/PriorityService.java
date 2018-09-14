package pl.coderslab.service;

import pl.coderslab.entity.Priority;

import java.util.List;

public interface PriorityService {
    List<Priority> findAll();

    Priority save(Priority priority);

    Priority setActiveOppositeToCurrent(int id);

    List<Priority> getAllActive();
}
