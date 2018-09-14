package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Action;
import pl.coderslab.repository.ActionRepository;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {
    private final ActionRepository actionRepository;

    @Autowired
    public ActionServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public List<Action> findFirst25ByOrderByTimestampDesc() {
        return actionRepository.findFirst25ByOrderByTimestampDesc();
    }

    @Override
    public Action save(Action action) {
        return actionRepository.save(action);
    }
}
