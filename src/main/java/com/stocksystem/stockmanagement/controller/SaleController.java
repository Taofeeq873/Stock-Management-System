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
        model.addAttribute("allSales", saleRepository.count());
        return "sale/list";
    }

    @RequestMapping(value = "/sales/create/{id}", method = RequestMethod.GET)
    public String create(@PathVariable("id") int id, Model model){

        model.addAttribute("customer", customerRepository.findAll());

        model.addAttribute("product", productRepository .findById(id).get());

//        model.addAttribute("product", productRepository .findAll());

        model.addAttribute("user", userRepository.findAll());

        return "sale/create";
    }

    @RequestMapping(value = "/sales/add",method = RequestMethod.POST)
    public String add(Model model, @RequestParam int id, @RequestParam String customer, @RequestParam double price, @RequestParam int salesQuantity, @RequestParam String user, @RequestParam double totalPrice, @RequestParam int quantity){

//        Product products = productRepository.findProductByName(product);

//        if(productQuantity < quantity) {
//            model.addAttribute("error", "Sales_Quantity cannot be more than the Product_Quantity");
//
//            return "sale/create";
//
//        }else {

            Product product = productRepository.findById(id).get();
            int product1 = product.getQuantity();


            Customer customers = customerRepository.findCustomerByEmail(customer);

            User users = userRepository.findUserByLastName(user);

//      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
//      System.out.println(takeOffTime);
//      Date date_sold = formatter.parse(dateSold);

            long millis = System.currentTimeMillis();
            Date dateSold = new Date(millis);

            Sale sale = new Sale(customers, product, dateSold, price, salesQuantity, users, totalPrice, quantity);
            int new_quantity = product1 - salesQuantity;
            product.setQuantity(new_quantity);

            saleRepository.save(sale);
            productRepository.save(product);


            return "redirect:/sales/list";
//        }
    }

//    @RequestMapping(value = "/sales/edit/{id}", method = RequestMethod.GET)
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//
//        model.addAttribute("sale", saleRepository.findById(id).get());
//        return "sale/edit";
//    }
//
//    @RequestMapping(value = "/sales/update", method = RequestMethod.POST)
//    public String updateSale(Model model, @RequestParam int id, @RequestParam int quantity){
//
//        //BeanUtils.copyProperties(aircraft, "id");
//
//        Sale sale= saleRepository.findById(id).get();
//
//        sale.setQuantity(quantity);
//
//        saleRepository.save(sale);
//
//        return "redirect:/sales/list";
//
//    }

    @RequestMapping(value = "/sales/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Sale sale = saleRepository.findById(id).get();

        saleRepository.delete(sale);

        return "redirect:/sales/list";
    }

}
