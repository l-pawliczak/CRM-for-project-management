package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.service.ActionService;
import pl.coderslab.service.ProjectService;

@Controller
public class MainController {

    private final ProjectService projectService;
    private final ActionService actionService;

    @Autowired
    public MainController(ProjectService projectService, ActionService actionService) {
        this.projectService = projectService;
        this.actionService = actionService;
    }

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("logs", actionService.findFirst25ByOrderByTimestampDesc());
        model.addAttribute("projects", projectService.findFirst5ByOrderByCreateDateDesc());

        return "index";
    }
}
