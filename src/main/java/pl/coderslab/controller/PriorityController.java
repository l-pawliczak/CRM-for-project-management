package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Priority;
import pl.coderslab.service.PriorityServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/priority")
public class PriorityController {
    private final PriorityServiceImpl priorityService;

    @Autowired
    public PriorityController(PriorityServiceImpl priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("")
    public String showAll(Model model){
        model.addAttribute("priorities", priorityService.findAll());

        return "priority/list";
    }

    @GetMapping("/add")
    public String addPriority(Model model) {
        model.addAttribute("priority", new Priority());

        return "priority/add";
    }

    @PostMapping("/add")
    public String addPriority(@Valid Priority priority, BindingResult result) {
        if (result.hasErrors()) {
            return "priority/add";
        }
        priorityService.save(priority);
        return "redirect:/priority";
    }


    @GetMapping(path = "/{id}/change")
    public String activate(@PathVariable int id) {
        priorityService.setActiveOppositeToCurrent(id);

        return "redirect:/priority/all";
    }
}
