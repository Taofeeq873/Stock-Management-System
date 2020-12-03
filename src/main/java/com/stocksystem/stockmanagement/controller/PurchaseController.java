package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.*;
import com.stocksystem.stockmanagement.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@RestController
@Controller
public class PurchaseController {
    final PurchaseRepository purchaseRepository;
    final ProductRepository productRepository;
    final SupplierRepository supplierRepository;
    final AvailableProductRepository availableProductRepository;
    final ProductTypeRepository productTypeRepository;

    public PurchaseController(PurchaseRepository purchaseRepository, ProductRepository productRepository, SupplierRepository supplierRepository, AvailableProductRepository availableProductRepository, ProductTypeRepository productTypeRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.availableProductRepository = availableProductRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @RequestMapping(value = "/purchases/list", method = RequestMethod.GET)
    public String purchase(Model model){
        model.addAttribute("purchases",purchaseRepository.findAll());
//      model.addAttribute("allPurchases", productRepository.count());
        model.addAttribute("supplier", supplierRepository.findAll());
        model.addAttribute("product", productRepository .findAll());
        model.addAttribute("productType", productTypeRepository.findAll());
        return "purchase/list";
    }

//    @RequestMapping(value = "/purchases/create", method = RequestMethod.GET)
//    public String create(Model model){
//
////        model.addAttribute("supplier", supplierRepository.findAll());
////
////        model.addAttribute("product", productRepository .findAll());
////
////        model.addAttribute("productType", productTypeRepository.findAll());
//
//        return "purchase/list";
//    }

    @RequestMapping(value = "/purchases/add",method = RequestMethod.POST)
    public String add(Model model, @RequestParam String supplier, @RequestParam String name, @RequestParam double price,@RequestParam int quantity, @RequestParam String productType, @RequestParam String description) {

//        Product products = productRepository.findProductByName(product);

        Supplier suppliers = supplierRepository.findSupplierByCompanyName(supplier);

        ProductType product_Type = productTypeRepository.findProductTypeByName(productType);

        long millis = System.currentTimeMillis();
        Date datePurchased = new Date(millis);

        Purchase purchase = new Purchase(suppliers,name,price,quantity,datePurchased,product_Type,description);
        purchaseRepository.save(purchase);

        Product product = new Product(name,supplier,quantity,description,productType,price,datePurchased);
        productRepository.save(product);


        return "redirect:/purchases/list";
    }

    @RequestMapping(value = "/purchases/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        //Aircraft aircraft = aircraftRepository.findById(id);

        model.addAttribute("purchase", purchaseRepository.findById(id).get());
        return "purchase/edit";
    }

    @RequestMapping(value = "/purchases/update", method = RequestMethod.POST)
    public String updatePurchase(Model model, @RequestParam int id, @RequestParam String name, @RequestParam double price,@RequestParam int quantity) {

        //BeanUtils.copyProperties(aircraft, "id");

        Purchase purchase= purchaseRepository.findById(id).get();

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
//        //System.out.println(takeOffTime);
//        Date date_purchased = formatter.parse(datePurchased);

        purchase.setName(name);
        purchase.setPrice(price);
        purchase.setQuantity(quantity);
//        purchase.setDatePurchased(date_purchased);

        purchaseRepository.save(purchase);

        return "redirect:/purchases/list";

    }

    @RequestMapping(value = "/purchases/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Purchase purchase = purchaseRepository.findById(id).get();

        purchaseRepository.delete(purchase);

        return "redirect:/purchases/list";
    }

//    //Create
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(path = "/purchase/create", method = RequestMethod.POST)
//    public String create(@RequestParam String supplier, @RequestParam String name, @RequestParam double price,@RequestParam int quantity, @RequestParam String productType, @RequestParam String description) {
//
//        Supplier suppliers = supplierRepository.findById(Integer.parseInt(supplier)).get();
//
//        ProductType product_Type = productTypeRepository.findById(Integer.parseInt(productType)).get();
//
//        long millis = System.currentTimeMillis();
//        Date datePurchased = new Date(millis);
//
//
//        Purchase purchase = new Purchase(suppliers,name,price,quantity,datePurchased,product_Type,description);
//        purchaseRepository.save(purchase);
//
//        return "Creation Successful";
//    }
//
//    //Read
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(path="/purchase/list/{id}", method = RequestMethod.GET)
//    public Purchase findPurchaseById(@PathVariable int id, Model model)
//    {
//        Purchase purchase = purchaseRepository.findById(id).get();
//        return purchase;
//    }
//
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(path="/purchase/list", method = RequestMethod.GET)
//    public List Purchase()
//    {
//        return (List)purchaseRepository.findAll();
//    }
//
//    //Update
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(path = "/purchase/update", method = RequestMethod.PUT)
//    public String update(@RequestParam int id, @RequestParam String name, @RequestParam double price,@RequestParam int quantity)
//    {
//        Purchase purchase = purchaseRepository.findById(id).get();
//
//        purchase.setName(name);
//        purchase.setPrice(price);
//        purchase.setQuantity(quantity);
//
//        purchaseRepository.save(purchase);
//
//        return "Successfully Updated";
//
//    }
//
//    //Delete
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(path = "/purchase/delete/{id}", method = RequestMethod.DELETE)
//    public String delete(@PathVariable int id)
//    {
//        Purchase purchase = purchaseRepository.findById(id).get();
//
//        purchaseRepository.delete(purchase);
//
//        return "Successfully Deleted";
//    }

}
