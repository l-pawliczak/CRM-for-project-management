package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.Task;
import pl.coderslab.service.ProjectService;
import pl.coderslab.service.TaskService;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/project/{id}/add")
    public String addTask(@PathVariable int id, Model model) {
        Project project = projectService.findById(id);
        model.addAttribute("users", project.getUsers());
        model.addAttribute("task", new Task());

        return "task/add";
    }

    @PostMapping("/add")
    public String addTask(@Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "task/add";
        }
        taskService.save(task);

        return "redirect: /task";
    }

    @GetMapping("")
    public String listTask(Model model) {
        model.addAttribute("tasks", taskService.findAll());

        return "task/list";
    }
}
