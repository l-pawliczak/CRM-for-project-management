package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;
import pl.coderslab.repository.ProjectRepository;
import pl.coderslab.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Project> findFirst5ByOrderByCreateDateDesc() {
        return projectRepository.findFirst5ByOrderByCreateDateDesc();
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(int id) {
        return projectRepository.findOne(id);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project addUsers(int projectId, Set<User> users) {
        Project project = findById(projectId);
        project.setUsers(users);

        return projectRepository.save(project);
    }

    @Override
    public Project removeUser(int projectId, int userId) {
        Project project = findById(projectId);
        project.getUsers().remove(userRepository.findOne(userId));

        return save(project);
    }

    @Override
    public Project setActiveOpposite(int projectId) {
        Project project = findById(projectId);
        project.setActive(!project.isActive());
        save(project);

        return project;
    }
}
