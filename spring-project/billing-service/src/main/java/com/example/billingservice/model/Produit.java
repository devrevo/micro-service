package com.example.billingservice.model;

import lombok.Data;

@Data
public class Produit {
    private Long id;
    private  String name;
    private  double price;
    private  double quantity;
}
