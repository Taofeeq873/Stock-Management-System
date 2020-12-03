package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.model.Supplier;
import com.stocksystem.stockmanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
public class SupplierController {
    final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

//
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(path="/suppliers/list", method = RequestMethod.GET)
//    public List Supplier()
//    {
//        return (List)supplierRepository.findAll();
//    }
//
//        //Read
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
//    @RequestMapping(value="/suppliers/list/{id}", method = RequestMethod.GET)
//    public Supplier getProductTypeById(@PathVariable int id)
//    {
//        Supplier supplier = supplierRepository.findById(id).get();
//        return supplier;
//    }

    @RequestMapping(value = "/suppliers/list", method = RequestMethod.GET)
    public String suppliers(Model model){
        model.addAttribute("suppliers",supplierRepository.findAll());
        return "supplier/list";
    }

    @RequestMapping(value = "/suppliers/create", method = RequestMethod.GET)
    public String create(Model model){
        return "supplier/list";
    }
    @RequestMapping(value = "/suppliers/add",method = RequestMethod.POST)
    public String add(Model model, @RequestParam String lastName, @RequestParam String firstName, @RequestParam String phone, @RequestParam String address, @RequestParam String email, @RequestParam String companyName){

        Supplier supplier = new Supplier (lastName,firstName,phone,address,email,companyName);
        supplierRepository.save(supplier);

        return "redirect:/suppliers/list";
    }
    @RequestMapping(value = "/suppliers/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        //Aircraft aircraft = aircraftRepository.findById(id);

        model.addAttribute("supplier", supplierRepository.findById(id).get());
        return "supplier/edit";
    }

    @RequestMapping(value = "/suppliers/update", method = RequestMethod.POST)
    public String updateSupplier(Model model, @RequestParam int id, @RequestParam String lastName, @RequestParam String firstName, @RequestParam String phone, @RequestParam String address, @RequestParam String email, @RequestParam String companyName) {

        //BeanUtils.copyProperties(aircraft, "id");

        Supplier supplier= supplierRepository.findById(id).get();
        supplier.setLastName(lastName);
        supplier.setFirstName(firstName);
        supplier.setPhone(phone);
        supplier.setAddress(address);
        supplier.setEmail(email);
        supplier.setCompanyName(companyName);
        supplierRepository.save(supplier);

        return "redirect:/suppliers/list";

    }

    @RequestMapping(value = "/suppliers/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Supplier supplier = supplierRepository.findById(id).get();

        supplierRepository.delete(supplier);
        return "redirect:/suppliers/list";
    }


}
