package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.Role;
import com.stocksystem.stockmanagement.model.Supplier;
import com.stocksystem.stockmanagement.model.User;
import com.stocksystem.stockmanagement.repository.RoleRepository;
import com.stocksystem.stockmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    final
    PasswordEncoder passwordEncoder;
    final UserRepository userRepository;
    final
    RoleRepository roleRepository;
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value = "/users/list", method = RequestMethod.GET)
    public String users(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @GetMapping("/users/create")
    public String create(Model model){
        return "user/register";
    }
    @PostMapping(value = "/users/register")
    public String register(Model model, RedirectAttributes redirectAttributes, com.stocksystem.stockmanagement.model.viewmodels.RegisterUserModel registerUserModel){
        //String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$";
        //Pattern p = Pattern.compile(regex);
       // Matcher m = p.matcher(registerUserModel.getPassword());
        if(!registerUserModel.getPassword().equals(registerUserModel.getConfirmPassword())){
            redirectAttributes.addAttribute("error","Password does not match ");
        } else if( userRepository.existsByUsername(registerUserModel.getUserName())){
            redirectAttributes.addAttribute("error","User with same id already exist ");
        }
        else if( registerUserModel.getPassword().isBlank()||registerUserModel.getPassword().isEmpty()){
            redirectAttributes.addAttribute("error","Password can not be empty or blank ");
      }

 //       else if( !m.matches()){
//            redirectAttributes.addAttribute("error","Password is not strong enough");
//        }
       else{
            User u = new User();
            u.setUsername(registerUserModel.getUserName());
            u.setLastName(registerUserModel.getLastName());
            u.setFirstName(registerUserModel.getFirstName());
            u.setEmail(registerUserModel.getEmail());
            u.setPassword(passwordEncoder.encode(registerUserModel.getPassword()));
            Optional<Role> optionalRole= roleRepository.findByName("USER");
            if(optionalRole.isPresent()) {
                Role role = optionalRole.get();
                List<Role> roleList = new ArrayList<>();
                roleList.add(role);
                u.setRoles(roleList);
            }
            userRepository.save(u);
            //redirectAttributes.addAttribute("error","");
            return "redirect:/login";
       }
        return "redirect:/users/create";
    }
    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        model.addAttribute("user", userRepository.findById(id).get());
        return "user/edit";
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public String updateUser(Model model, @RequestParam long id, @RequestParam String lastName, @RequestParam String firstName, @RequestParam String email) {

        //BeanUtils.copyProperties(aircraft, "id");

        User user= userRepository.findById(id).get();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setEmail(email);
        userRepository.save(user);

        return "redirect:/users/list";

    }

    @RequestMapping(value = "/users/assignRole/{id}", method = RequestMethod.GET)
    public String showAssignRoleForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", userRepository.findById(id).get());
        return "user/assignRole";
    }

    @RequestMapping(value = "/users/assignNewRole/{id}", method = RequestMethod.POST)
    public String updateRole(Model model, @PathVariable long id, @RequestParam String name) {

        User user = userRepository.findById(id).get();

        Role role = roleRepository.findByName(name).get();

        List<Role> roleList = user.getRoles();
        roleList.add(role);

        user.setRoles(roleList);

        userRepository.save(user);

        return "redirect:/users/list";
    }


    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") long id, Model model) {

        User user = userRepository.findById(id).get();

        userRepository.delete(user);
        return "redirect:/users/list";
    }
}
