package com.example.billingservice;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.ProductItem;
import com.example.billingservice.feigns.CustomerServiceClient;
import com.example.billingservice.feigns.InventoryServiceClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Produit;
import com.example.billingservice.repositries.Billrepository;
import com.example.billingservice.repositries.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}
    @Bean
	CommandLineRunner start(Billrepository billrepository ,
							ProductItemRepository productItemRepository,
	                      CustomerServiceClient customerServiceClient,
							InventoryServiceClient inventoryServiceClient,
							RepositoryRestConfiguration repositoryRestConfiguration
	){
	return  args -> {
		repositoryRestConfiguration.exposeIdsFor(Bill.class);
		Customer c  = new Customer();
		c=customerServiceClient.findCustomerbyID(1L);
		Bill B = new Bill(null,new Date(),null,c.getId(),null);
		B.setCustomerID(c.getId());
		 Bill b= billrepository.save(B);
		PagedModel<Produit> produitPagedModel = inventoryServiceClient.findAll();
		produitPagedModel.forEach(p->{
			ProductItem bi = new ProductItem();
			bi.setPrice(p.getPrice());
			bi.setQuantity(1+new Random().nextInt(100));
			bi.setProductID(p.getId());
			bi.setBill(b);
			productItemRepository.save(bi);
		});

	};

	}
}
