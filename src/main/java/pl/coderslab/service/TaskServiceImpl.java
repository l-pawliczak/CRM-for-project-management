package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Task;
import pl.coderslab.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Task findOne(int id) {
        return taskRepository.findOne(id);
    }
}
