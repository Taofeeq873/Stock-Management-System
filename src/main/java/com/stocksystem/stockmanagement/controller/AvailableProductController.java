//package com.stocksystem.stockmanagement.controller;
//
//import com.stocksystem.stockmanagement.model.AvailableProduct;
//import com.stocksystem.stockmanagement.repository.AvailableProductRepository;
//import com.stocksystem.stockmanagement.repository.PurchaseRepository;
//import com.stocksystem.stockmanagement.repository.SupplierRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class AvailableProductController {
//
//    final AvailableProductRepository availableProductRepository;
//    final PurchaseRepository purchaseRepository;
//    final SupplierRepository supplierRepository;
//
//    public AvailableProductController(AvailableProductRepository availableProductRepository, PurchaseRepository purchaseRepository, SupplierRepository supplierRepository) {
//        this.availableProductRepository = availableProductRepository;
//        this.purchaseRepository = purchaseRepository;
//        this.supplierRepository = supplierRepository;
//    }
//
//    @RequestMapping(value = "/availableProducts/list", method = RequestMethod.GET)
//    public String purchase(Model model){
//        model.addAttribute("availableProducts",availableProductRepository.findAll());
//        model.addAttribute("allAvailableProducts", availableProductRepository.count());
//        return "availableProduct/list";
//    }
//
//
////    @RequestMapping(value = "/availableProducts/delete/{id}", method = RequestMethod.GET)
////    public String remove(@PathVariable("id") int id, Model model) {
////
////        AvailableProduct availableProduct = availableProductRepository.findById(id).get();
////
////        availableProductRepository.delete(availableProduct);
////
////        return "redirect:/availableProducts/list";
////    }
//
//
//}
