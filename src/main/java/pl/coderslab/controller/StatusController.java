package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Status;
import pl.coderslab.service.StatusService;

import javax.validation.Valid;

@Controller
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("")
    public String showAll(Model model) {
        model.addAttribute("statuses", statusService.findAll());

        return "status/list";
    }

    @GetMapping("/add")
    public String addStatus(Model model) {
        model.addAttribute("status", new Status());

        return "status/add";
    }

    @PostMapping("/add")
    public String addStatus(@Valid Status status, BindingResult result) {
        if (result.hasErrors()) {
            return "status/add";
        }
        statusService.save(status);

        return "redirect:/status";
    }

    @GetMapping(path = "/{id}/active")
    public String activate(@PathVariable int id) {
        statusService.reverseStatus(id);

        return "redirect:/status";
    }
}
