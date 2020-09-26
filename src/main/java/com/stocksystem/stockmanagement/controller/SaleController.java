package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.*;
import com.stocksystem.stockmanagement.repository.CustomerRepository;
import com.stocksystem.stockmanagement.repository.ProductRepository;
import com.stocksystem.stockmanagement.repository.SaleRepository;
import com.stocksystem.stockmanagement.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SaleController {
    final SaleRepository saleRepository;
    final UserRepository userRepository;
    final CustomerRepository customerRepository;
    final ProductRepository productRepository;

    public SaleController(SaleRepository saleRepository, UserRepository userRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/sales/list", method = RequestMethod.GET)
    public String sale(Model model){
        model.addAttribute("sales",saleRepository.findAll());
        return "sale/list";
    }

    @RequestMapping(value = "/sales/create", method = RequestMethod.GET)
    public String create(Model model){

        model.addAttribute("customer", customerRepository.findAll());

        model.addAttribute("product", productRepository .findAll());

        model.addAttribute("user", userRepository .findAll());

        return "sale/create";
    }

    @RequestMapping(value = "/sales/add",method = RequestMethod.POST)
    public String add(Model model, @RequestParam String customer, @RequestParam String product,@RequestParam String dateSold, @RequestParam double price, @RequestParam int quantity, @RequestParam String user) throws ParseException {

        Product products = productRepository.findProductByName(product);

        Customer customers = customerRepository.findCustomerByEmail(customer);

        User users = userRepository.findUserByLastName(user);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        //System.out.println(takeOffTime);
        Date date_sold = formatter.parse(dateSold);

        Sale sale = new Sale(customers,products,date_sold,price,quantity,users);
        saleRepository.save(sale);

        return "redirect:/sales/list";
    }

    @RequestMapping(value = "/sales/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("sale", saleRepository.findById(id).get());
        return "sale/edit";
    }

    @RequestMapping(value = "/sales/update", method = RequestMethod.POST)
    public String updateSale(Model model, @RequestParam int id,@RequestParam String dateSold, @RequestParam double price,@RequestParam int quantity) throws ParseException {

        //BeanUtils.copyProperties(aircraft, "id");

        Sale sale= saleRepository.findById(id).get();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        //System.out.println(takeOffTime);
        Date date_sold = formatter.parse(dateSold);

        sale.setPrice(price);
        sale.setQuantity(quantity);
        sale.setDateSold(date_sold);

        saleRepository.save(sale);

        return "redirect:/sales/list";

    }

    @RequestMapping(value = "/sales/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Sale sale = saleRepository.findById(id).get();

        saleRepository.delete(sale);

        return "redirect:/sales/list";
    }


}
