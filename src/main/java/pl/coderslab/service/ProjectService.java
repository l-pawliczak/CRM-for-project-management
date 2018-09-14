package pl.coderslab.service;

import pl.coderslab.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findFirst5ByOrderByCreateDateDesc();

    List<Project> findAll();

    Project findById(int id);

    Project save(Project project);
}
