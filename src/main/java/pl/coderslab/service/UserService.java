package pl.coderslab.service;

import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    List<User> getAllByProjectsId(Project project);
}
