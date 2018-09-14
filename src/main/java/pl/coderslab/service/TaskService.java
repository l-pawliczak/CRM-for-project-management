package pl.coderslab.service;

import pl.coderslab.entity.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);

    List<Task> findAll();

    void delete(Task task);

    Task findOne(int id);
}
