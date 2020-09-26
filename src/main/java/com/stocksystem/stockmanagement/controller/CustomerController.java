package com.stocksystem.stockmanagement.controller;


import com.stocksystem.stockmanagement.model.Customer;
import com.stocksystem.stockmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @RequestMapping(value = "/customers/list", method = RequestMethod.GET)
    public String customers(Model model){
        model.addAttribute("customers",customerRepository.findAll());
        /*model.addAttribute("message","Thank You For Flying With Us");*/
        return "customer/list";
    }
    @RequestMapping(value = "/customers/create", method = RequestMethod.GET)
    public String create(Model model){
        return "customer/create";
    }
    @RequestMapping(value = "/customers/add",method = RequestMethod.POST)
    public String add(Model model, @RequestParam String lastName, @RequestParam String firstName, @RequestParam String phone, @RequestParam String address, @RequestParam String email){

        Customer customer = new Customer (lastName,firstName,phone,address,email);
        customerRepository.save(customer);

        return "redirect:/customers/list";
    }
    @RequestMapping(value = "/customers/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        //Aircraft aircraft = aircraftRepository.findById(id);

        model.addAttribute("customer", customerRepository.findById(id).get());
        return "customer/edit";
    }

    @RequestMapping(value = "/customers/update", method = RequestMethod.POST)
    public String updateCustomer(Model model, @RequestParam int id, @RequestParam String lastName, @RequestParam String firstName, @RequestParam String phone, @RequestParam String address, @RequestParam String email) {

        //BeanUtils.copyProperties(aircraft, "id");

        Customer customer= customerRepository.findById(id).get();

        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setEmail(email);
        customerRepository.save(customer);

        return "redirect:/customers/list";

    }

    @RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Customer customer = customerRepository.findById(id).get();

        customerRepository.delete(customer);
        return "redirect:/customers/list";
    }

}

