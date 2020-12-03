package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.ProductType;
import com.stocksystem.stockmanagement.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller

public class ProductTypeController {

    @Autowired
    private ProductTypeRepository productTypeRepository;

//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(path="/productTypes/list", method = RequestMethod.GET)
//    public List ProductType()
//    {
//        return (List)productTypeRepository.findAll();
//    }

//        //Read
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(value="/productTypes/list/{id}", method = RequestMethod.GET)
//    public ProductType ProductTypeById(@PathVariable int id)
//    {
//        ProductType productType = productTypeRepository.findById(id).get();
//        return productType;
//    }

    @RequestMapping(value = "/productTypes/list", method = RequestMethod.GET)
    public String productType(Model model){
        model.addAttribute("productTypes",productTypeRepository.findAll());
        model.addAttribute("allProductTypes", productTypeRepository.count());
        return "productType/list";
    }
    @RequestMapping(value = "/productTypes/create", method = RequestMethod.GET)
    public String create(Model model){
        return "productType/list";
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

//    //Create
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(value = "/productType", method = RequestMethod.POST)
//    public String create(@RequestParam String name){
//        ProductType productType = new ProductType(name);
//        productTypeRepository.save(productType);
//        return "Creation Successful";
//    }

//    //Update
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(path = "/productType/update", method = RequestMethod.PUT)
//    public String update(@RequestParam int id, @RequestParam String name)
//    {
//        ProductType productType = productTypeRepository.findById(id).get();
//        productType.setName(name);
//
//        productTypeRepository.save(productType);
//
//        return "Successfully Updated";
//
//    }
//
//    //Delete
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(path = "/productType/{id}", method = RequestMethod.DELETE)
//    public String delete(@PathVariable int id)
//    {
//        ProductType productType = productTypeRepository.findById(id).get();
//
//        productTypeRepository.delete(productType);
//
//        return "Successfully Deleted";
//    }


//        @RequestMapping(path="/productTypes", method=RequestMethod.GET)
//        public List<ProductType> getAllProductTypes(){
//            return (List<ProductType>) productTypeRepository.findAll();
//        }
//            @RequestMapping(value = "/productType/{id}", method = RequestMethod.GET)
//            public ProductType getProductTypeById(@PathVariable("id") int id){
//                return productTypeRepository.findById(id).get();
//            }
//}


