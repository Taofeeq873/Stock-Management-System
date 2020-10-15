package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.repository.*;
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
    ProductTypeRepository productTypeRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AvailableProductRepository availableProductRepository;

//    @Autowired
//    private LocationRepository locationRepository;

    @GetMapping("/")
    public String home(Model model){
        return "/home";
    }
    @GetMapping("/profile/{lastName}")
    public String profile(@PathVariable("lastName") String lastName, Model model){
        model.addAttribute("user", userRepository.findUserByLastName(lastName));
        return "profile";
    }
    @GetMapping("/about")
    public String about(Model model){
        return "/about";
    }
    @GetMapping("/contactUs")
    public String contactUs(Model model){
        return "/contactUs";
    }
    @GetMapping("/index")

    public String index(Model model){

//        int count = productTypeRepository.allProductTypes();
//        model.addAttribute("count", count);

        model.addAttribute("allProducts", productRepository.count());
        model.addAttribute("allAvailableProducts", availableProductRepository.count());

        return "/index";
    }

}
