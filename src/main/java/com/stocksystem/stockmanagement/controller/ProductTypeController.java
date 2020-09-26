package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.ProductType;
import com.stocksystem.stockmanagement.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductTypeController {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @RequestMapping(value = "/productTypes/list", method = RequestMethod.GET)
    public String productType(Model model){
        model.addAttribute("productTypes",productTypeRepository.findAll());
        /*model.addAttribute("message","Thank You For Flying With Us");*/
        return "productType/list";
    }
    @RequestMapping(value = "/productTypes/create", method = RequestMethod.GET)
    public String create(Model model){
        return "productType/create";
    }
    @RequestMapping(value = "/productTypes/add",method = RequestMethod.POST)
    public String add(Model model, @RequestParam String name){

        ProductType productType = new ProductType(name);
        productTypeRepository.save(productType);

        return "redirect:/productTypes/list";
    }
    @RequestMapping(value = "/productTypes/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        //Aircraft aircraft = aircraftRepository.findById(id);

        model.addAttribute("productType", productTypeRepository.findById(id).get());
        return "productType/edit";
    }

    @RequestMapping(value = "/productTypes/update", method = RequestMethod.POST)
    public String updateProductType(Model model, @RequestParam int id, @RequestParam String name) {

        //BeanUtils.copyProperties(aircraft, "id");

        ProductType productType= productTypeRepository.findById(id).get();
        productType.setName(name);
        productTypeRepository.save(productType);

        return "redirect:/productTypes/list";

    }

    @RequestMapping(value = "/productTypes/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        ProductType productType = productTypeRepository.findById(id).get();

        productTypeRepository.delete(productType);
        return "redirect:/productTypes/list";
    }

}
