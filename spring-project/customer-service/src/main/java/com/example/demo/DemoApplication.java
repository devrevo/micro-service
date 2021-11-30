package com.example.demo;

import com.example.demo.Repository.CustomerRespository;
import com.example.demo.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRespository cp , RepositoryRestConfiguration rc){
     return  args -> {
         rc.exposeIdsFor(Customer.class);
         cp.save(new Customer(null,"hassan","feyd@gmail.com"));
         cp.save(new Customer(null,"ABAQI","kbabra@gmail.com"));
         cp.save(new Customer(null,"yassir","kasimi@gmail.com"));
         cp.save(new Customer(null,"ayoub","mafkoud@gmail.com"));
         cp.save(new Customer(null,"anis","mohalhal@gmail.com"));
         cp.save(new Customer(null,"merrakchi","ghouzraf@gmail.com"));
         cp.findAll().forEach(customer -> {
             System.out.println(customer.getName());
         });

     };
    }

}
