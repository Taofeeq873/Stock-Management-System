package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("locations", locationRepository.findAll());
        return "/index";
    }

}
