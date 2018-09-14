package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.User;
import pl.coderslab.service.ProjectService;
import pl.coderslab.service.UserService;

import javax.validation.Valid;
import java.util.Set;

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

    @GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        model.addAttribute("project", projectService.findById(id));

        return "/project/details";
    }

    @GetMapping(path = "/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("project", projectService.findById(id));

        return "/project/edit";
    }

    @PostMapping(path = "/edit")
    public String edit(@Valid Project project, BindingResult result) {
        if (result.hasErrors()) {
            return "/project/edit";
        }
        projectService.save(project);

        return "redirect:/project";
    }

    @GetMapping(path = "/{id}/user/add")
    public String addUserForm(@PathVariable int id, Model model) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);

        return "/project/add_user";
    }

    @PostMapping(path = "/{id}/user/add")
    public String addUser(@PathVariable int id, @RequestParam Set<User> users) {
        projectService.addUsers(id, users);

        return "redirect:/project/" + id;
    }

    @GetMapping(path = "/{projectId}/user/delete/{userId}")
    public String removeUser(@PathVariable int projectId, @PathVariable int userId) {
        projectService.removeUser(projectId, userId);

        return "redirect:/project/" + projectId;
    }

    @ModelAttribute("users")
    public void getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
    }
}
