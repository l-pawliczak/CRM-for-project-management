package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Project;
import pl.coderslab.service.ProjectService;
import pl.coderslab.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("projects", projectService.findAll());

        return "project/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("project", new Project());

        return "project/add";
    }

    @PostMapping("/add")
    public String add(@Valid Project project, BindingResult result) {
        if (result.hasErrors()) {
            return "project/add";
        }
        project.setIdentity(project.getName().replaceAll("[^\\p{ASCII}]", "").replaceAll("\\s", "-"));
        projectService.save(project);

        return "redirect: /project";
    }

    @ModelAttribute("users")
    public void getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
    }
}
