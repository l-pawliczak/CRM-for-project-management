package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Priority;
import pl.coderslab.repository.PriorityRepository;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    private PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    @Override
    public Priority save(Priority priority) {
        return priorityRepository.save(priority);
    }
}
