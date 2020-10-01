package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.repository.LocationRepository;
import com.stocksystem.stockmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("locations", locationRepository.findAll());
        return "/index";
    }
    @GetMapping("/admins/dashboard/{lastName}")
    public String admin(@PathVariable("lastName") String lastName, Model model){
        model.addAttribute("user", userRepository.findUserByLastName(lastName));
        return "/admin/dashboard";
    }

}
