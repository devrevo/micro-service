package com.example.productservice;

import com.example.productservice.entities.Produit;
import com.example.productservice.repositorys.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication

public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner strat(ProduitRepository pr , RepositoryRestConfiguration repositoryRestConfiguration){
        return  args -> {
            repositoryRestConfiguration.exposeIdsFor(Produit.class);
            pr.save(new Produit(null,"ordinateur hp",3000,50));
            pr.save(new Produit(null,"imprement epson",4000,50));
            pr.save(new Produit(null,"sumsung 12",6000,20));
            pr.findAll().forEach(produit -> {
                System.out.println(produit.getName());
            });
        };
    }
}
