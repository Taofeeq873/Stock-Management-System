package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.*;
import com.stocksystem.stockmanagement.repository.ProductRepository;
import com.stocksystem.stockmanagement.repository.ProductTypeRepository;
import com.stocksystem.stockmanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class ProductController {

    final ProductRepository productRepository;
    final ProductTypeRepository productTypeRepository;
    final SupplierRepository supplierRepository;

    public ProductController(ProductRepository productRepository, ProductTypeRepository productTypeRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
        this.supplierRepository = supplierRepository;
    }

    @RequestMapping(value = "/products/list", method = RequestMethod.GET)
    public String product(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "product/list";
    }
    @RequestMapping(value = "/products/availableProducts", method = RequestMethod.GET)
    public String availableProducts(Model model, @RequestParam String name, @RequestParam String productType, @RequestParam int quantity) {

        ProductType product_Type = productTypeRepository.findProductTypeByName(productType);

        model.addAttribute("available_products", productRepository.searchAvailableProducts(name, product_Type, quantity));
        return "product/availableProducts";
    }
    @RequestMapping(value = "/products/create", method = RequestMethod.GET)
    public String create(Model model){

        model.addAttribute("supplier", supplierRepository.findAll());

        model.addAttribute("productType", productTypeRepository.findAll());

        return "product/create";
    }
    @RequestMapping(value = "/products/add",method = RequestMethod.POST)
    public String add(Model model, @RequestParam String name, @RequestParam String productType, @RequestParam int quantity,@RequestParam String status,@RequestParam String supplier, @RequestParam double price){

        ProductType product_Type = productTypeRepository.findProductTypeByName(productType);

        Supplier suppliers = supplierRepository.findSupplierByCompanyName(supplier);

        Product product = new Product(name,product_Type,quantity,status,suppliers,price);

        productRepository.save(product);

        return "redirect:/products/list";
    }
    @RequestMapping(value = "/products/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("product", productRepository.findById(id).get());

        model.addAttribute("productType", productTypeRepository.findAll());

        model.addAttribute("supplier", supplierRepository.findAll());

        return "product/edit";
    }

    @RequestMapping(value = "/products/update", method = RequestMethod.POST)
    public String updateProduct(Model model, @RequestParam int id, @RequestParam String name,@RequestParam String productType, @RequestParam int quantity,@RequestParam String status,@RequestParam double price) {

        //BeanUtils.copyProperties(aircraft, "id");

        Product product= productRepository.findById(id).get();

        ProductType product_Type = productTypeRepository.findProductTypeByName(productType);

        product.setName(name);
        product.setQuantity(quantity);
        product.setStatus(status);
        product.setProductType(product_Type);
        product.setPrice(price);

        productRepository.save(product);

        return "redirect:/products/list";

    }

    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Product product = productRepository.findById(id).get();

        productRepository.delete(product);
        return "redirect:/products/list";
    }


}
