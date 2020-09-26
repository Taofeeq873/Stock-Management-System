package com.stocksystem.stockmanagement;

import com.stocksystem.stockmanagement.model.Customer;
import com.stocksystem.stockmanagement.model.Supplier;
import com.stocksystem.stockmanagement.repository.CustomerRepository;
import com.stocksystem.stockmanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockmanagementsystemApplication /*implements CommandLineRunner*/ {

    @Autowired
    private SupplierRepository supplierRepository;
    public static void main(String[] args) {
        SpringApplication.run(StockmanagementsystemApplication.class, args);
    }

//    @Override
//    public void run(String...args) throws Exception{
//        if(customerRepository.findCustomerByEmail("azeeztaofeeq873@gmail.com")==null){
//            Customer customer = new Customer("Azeez","Taofeeq","08034121786","12,Tejumade Crescent","azeeztaofeeq873@gmail.com");
//            customerRepository.save(customer);
//        }
//
//    }

//    @Override
//    public void run(String...args) throws Exception{
//        if(supplierRepository.findSupplierByEmail("azeeztaofeeq873@gmail.com")==null){
//            Supplier supplier = new Supplier("Adepo","Hafeez","08034121786","12,Tejumade Crescent","adepohafeez33@gmail.com");
//            supplierRepository.save(supplier);
//        }
//
//    }
}
