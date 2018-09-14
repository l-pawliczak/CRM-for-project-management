package pl.coderslab.service;

import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    List<Project> findFirst5ByOrderByCreateDateDesc();

    List<Project> findAll();

    Project findById(int id);

    Project save(Project project);

    Project addUsers(int projectId, Set<User> users);

    Project removeUser(int projectId, int userId);

    Project setActiveOpposite(int projectId);
}
