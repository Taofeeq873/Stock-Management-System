package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.Role;
import com.stocksystem.stockmanagement.model.User;
import com.stocksystem.stockmanagement.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    final UserRepository userRepository;
    final ProductTypeRepository productTypeRepository;
    final ProductRepository productRepository;
    final AvailableProductRepository availableProductRepository;
    final CustomerRepository customerRepository;
//    final Authentication authentication;

    public IndexController(UserRepository userRepository, ProductTypeRepository productTypeRepository, ProductRepository productRepository, AvailableProductRepository availableProductRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.productTypeRepository = productTypeRepository;
        this.productRepository = productRepository;
        this.availableProductRepository = availableProductRepository;
        this.customerRepository = customerRepository;
//        this.authentication = authentication;
    }

    @GetMapping("/")
    public String home(Model model){
        return "/home";
    }

//    @GetMapping("/productTypes/create")
//    public String create(Model model){
//        return "/productType/create";
//    }
//
//    @GetMapping("/productTypes/list")
//    public String testing(Model model){
//        return "/testing";
//    }

    public String getSignedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if(principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }else {
            username = principal.toString();
        }
        return username;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String profile(Model model, Authentication authentication){

        String username = authentication.getName();

        String name = getSignedUser();
        User u = userRepository.findUserByUsername(name);
        List<String> roles = new ArrayList<>();
        for(Role r : u.getRoles()){
            roles.add(r.getName());
        }

        model.addAttribute("user", userRepository.findUserByUsername(username));
        model.addAttribute("roles", roles);
        return "details";
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
        model.addAttribute("allCustomers", customerRepository.count());
        model.addAttribute("allProductTypes", productTypeRepository.count());

        return "/index";
    }

}
