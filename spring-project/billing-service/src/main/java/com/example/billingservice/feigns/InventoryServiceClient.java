package com.example.billingservice.feigns;

import com.example.billingservice.model.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface InventoryServiceClient {
    @GetMapping("/produits/{id}")
    Produit findProductById(@PathVariable("id") Long id);
    @GetMapping("/produits")
    PagedModel<Produit> findAll();
}
