package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.*;
import com.stocksystem.stockmanagement.repository.*;
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
        model.addAttribute("allPurchases", productRepository.count());
        return "purchase/list";
    }

    @RequestMapping(value = "/purchases/create", method = RequestMethod.GET)
    public String create(Model model){

        model.addAttribute("supplier", supplierRepository.findAll());

        model.addAttribute("product", productRepository .findAll());

        model.addAttribute("productType", productTypeRepository.findAll());


        return "purchase/create";
    }

    @RequestMapping(value = "/purchases/add",method = RequestMethod.POST)
    public String add(Model model, @RequestParam String supplier, @RequestParam String name, @RequestParam double price,@RequestParam int quantity, @RequestParam String productType) {

//        Product products = productRepository.findProductByName(product);

        Supplier suppliers = supplierRepository.findSupplierByCompanyName(supplier);

        ProductType product_Type = productTypeRepository.findProductTypeByName(productType);

        long millis = System.currentTimeMillis();
        Date datePurchased = new Date(millis);

        Purchase purchase = new Purchase(suppliers,name,price,quantity,datePurchased,product_Type);
        purchaseRepository.save(purchase);

        AvailableProduct availableProduct = new AvailableProduct(name,supplier,quantity,datePurchased,productType);
        availableProductRepository.save(availableProduct);


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


}
