package com.stocksystem.stockmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class StockmanagementsystemApplication /*implements CommandLineRunner*/ {

    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(StockmanagementsystemApplication.class, args);
    }

//    @Override
//    public void run(String...args) throws Exception{
//        if(customerRepository.findCustomerByEmail("azeeztaofeeq873@gmail.com")==null){
//            Customer customer = new Customer("Azeez","Taofeeq","08034121786","12,Tejumade Crescent","azeeztaofeeq873@gmail.com");
//            customerRepository.save(customer);
//        }
////
////    }

//    @Override
//    public void run(String...args) throws Exception{
//        if(supplierRepository.findSupplierByEmail("azeeztaofeeq873@gmail.com")==null){
//            Supplier supplier = new Supplier("Adepo","Hafeez","08034121786","12,Tejumade Crescent","adepohafeez33@gmail.com");
//            supplierRepository.save(supplier);
//        }
//
//    }
//        @Bean
//        public WebMvcConfigurer corsConfigurer() {
//            return new WebMvcConfigurer() {
//                @Override
//                public void addCorsMappings(CorsRegistry registry) {
//                    registry.addMapping("/productTypes").allowedOrigins("http://localhost:8080");
//                }
//            };
//        }
}
