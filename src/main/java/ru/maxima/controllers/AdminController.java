package ru.maxima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maxima.model.Person;
import ru.maxima.service.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PeopleService service;

    @Autowired
    public AdminController(PeopleService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAdminPage(Model model , @ModelAttribute("person")Person person){
        List<Person> allPeople = service.getAllPeople();
        model.addAttribute("allPeople" , allPeople);
        return "admin-page";
    }

    @PostMapping()
    public String makeAdmin(@ModelAttribute("person")Person person){
        service.upgradeToAdmin(person);
        return "redirect:/people";
    }
}
