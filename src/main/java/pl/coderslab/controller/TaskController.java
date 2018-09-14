package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Action;
import pl.coderslab.entity.Project;
import pl.coderslab.entity.Task;
import pl.coderslab.service.ActionService;
import pl.coderslab.service.ProjectService;
import pl.coderslab.service.TaskService;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final ProjectService projectService;
    private final TaskService taskService;
    private final ActionService actionService;

    public TaskController(ProjectService projectService, TaskService taskService, ActionService actionService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.actionService = actionService;
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
        actionService.save(new Action("New task added to project: " + task.getProject().getName() + ", task name: " + task.getSubject(), task.getProject()));
        taskService.save(task);

        return "redirect: /task";
    }

    @GetMapping("")
    public String listTask(Model model) {
        model.addAttribute("tasks", taskService.findAll());

        return "task/list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        model.addAttribute("task", taskService.findOne(id));

        return "task/details";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("task", taskService.findOne(id));

        return "task/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "/task/edit";
        }
        actionService.save(new Action("Task edited in project: " + task.getProject().getName() + ", task name: " + task.getSubject(), task.getProject()));
        taskService.save(task);

        return "redirect:/task";
    }
}
