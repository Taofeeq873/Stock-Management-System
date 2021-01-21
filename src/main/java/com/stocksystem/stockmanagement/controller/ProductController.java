package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.*;
import com.stocksystem.stockmanagement.repository.*;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class ProductController {

    final ProductRepository productRepository;
    final ProductTypeRepository productTypeRepository;
    final SupplierRepository supplierRepository;
    final PurchaseRepository purchaseRepository;
    final AvailableProductRepository availableProductRepository;

    public ProductController(ProductRepository productRepository, ProductTypeRepository productTypeRepository, SupplierRepository supplierRepository, PurchaseRepository purchaseRepository, AvailableProductRepository availableProductRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
        this.supplierRepository = supplierRepository;
        this.purchaseRepository = purchaseRepository;
        this.availableProductRepository = availableProductRepository;
    }

    @RequestMapping(value = "/products/list", method = RequestMethod.GET)
    public String product(Model model){

//        List<Product> products = (List<Product>) productRepository.findAll();
//         for (Product p : products){
//             if (p.getQuantity() == 0 ){
//                 model.addAttribute("enable" ,"enable");
//                 productRepository.delete(p);
//             }
//         }

        model.addAttribute("products",productRepository.findAll());
        model.addAttribute("allProducts", productRepository.count());

        return "product/list";
    }

//    @RequestMapping(value = "/products/create/{id}", method = RequestMethod.GET)
//    public String create(@PathVariable("id") int id, Model model){
//
////        model.addAttribute("availableProduct", availableProductRepository .findById(id).get());
//
//        model.addAttribute("purchase", purchaseRepository .findById(id).get());
////        model.addAttribute("supplier", supplierRepository.findAll());
////        model.addAttribute("productType", productTypeRepository.findAll());
//
//        return "product/create";
//    }

//    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
//    public String add(Model model,@RequestParam int id, @RequestParam String name, @RequestParam String productType, @RequestParam int quantity, @RequestParam String description, @RequestParam String supplier, @RequestParam double price){
//
////        Supplier suppliers = supplierRepository.findSupplierByCompanyName(supplier);
////        AvailableProduct availableProduct = availableProductRepository.findById(id).get();
//        Purchase purchase = purchaseRepository.findById(id).get();
//
//        if(productQuantity > purchase.getQuantity()) {
//            model.addAttribute("error", "Sales Quantity cannot be more than the Stock Quantity");
//
//            return "product/create";
//
//        }else {
//
////          AvailableProduct availableProduct = availableProductRepository.findById(id).get();
////          AvailableProduct availableProduct = availableProductRepository.findAvailableProductByName(name);
//            int product_quantity = purchase.getQuantity();
//
////          ProductType product_Type = productTypeRepository.findProductTypeByName(productType);
//
//            long millis = System.currentTimeMillis();
//            Date dateCreated = new Date(millis);
//
//            Product product = new Product(name, productType, quantity, description, supplier, price, dateCreated, purchase);
//            int new_quantity = product_quantity - productQuantity;
//            purchase.setQuantity(new_quantity);
//
//            productRepository.save(product);
//            purchaseRepository.save(purchase);
//
//            return "redirect:/products/list";
//        }
//    }
//
//    @RequestMapping(value = "/products/edit/{id}", method = RequestMethod.GET)
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//
//        model.addAttribute("product", productRepository.findById(id).get());
//
////        model.addAttribute("productType", productTypeRepository.findAll());
//
////        model.addAttribute("supplier", supplierRepository.findAll());
//
//        return "product/edit";
//    }

//    @RequestMapping(value = "/products/update", method = RequestMethod.POST)
//    public String updateProduct(Model model, @RequestParam int id, @RequestParam int productQuantity,@RequestParam String description,@RequestParam double price) {
//
//        //BeanUtils.copyProperties(aircraft, "id");
//
//        Product product= productRepository.findById(id).get();
//
////        ProductType product_Type = productTypeRepository.findProductTypeByName(productType);
//
//
//        product.setProductQuantity(productQuantity);
//        product.setDescription(description);
////        product.setProductType(productType);
//        product.setPrice(price);
//
//        productRepository.save(product);
//
//        return "redirect:/products/list";
//
//    }

    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") long id, Model model) {

        Product product = productRepository.findById(id).get();

        productRepository.delete(product);

        return "redirect:/products/list";
    }
}
