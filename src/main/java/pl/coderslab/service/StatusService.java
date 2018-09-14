package pl.coderslab.service;

import pl.coderslab.entity.Status;

import java.util.List;

public interface StatusService {
    List<Status> findAll();

    Status findById(int id);

    Status save(Status status);

    Status reverseStatus(int id);
}
