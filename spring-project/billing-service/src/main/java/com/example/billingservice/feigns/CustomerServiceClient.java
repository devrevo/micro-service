package com.example.billingservice.feigns;

import com.example.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceClient {
     @GetMapping(path = "/customers/{id}")
    Customer findCustomerbyID(@PathVariable("id") Long id);
}
