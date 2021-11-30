package com.example.billingservice.web;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.feigns.CustomerServiceClient;
import com.example.billingservice.feigns.InventoryServiceClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Produit;
import com.example.billingservice.repositries.Billrepository;
import com.example.billingservice.repositries.ProductItemRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class Fullbill {
    private Billrepository billrepository;
    private ProductItemRepository productItemRepository;
    private CustomerServiceClient customerServiceClient;
    private InventoryServiceClient inventoryServiceClient;


    public Fullbill(Billrepository billrepository, ProductItemRepository productItemRepository, CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient) {
        this.billrepository = billrepository;
        this.productItemRepository = productItemRepository;
        this.customerServiceClient = customerServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
    }
    @GetMapping(path ="/fullbill/{id}")
    public Bill Getbill(@PathVariable(name = "id") long id){

        Bill bill =  billrepository.findById(id).get();
        Customer customer = customerServiceClient.findCustomerbyID(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(pi->{
            Produit p = inventoryServiceClient.findProductById(pi.getProductID());
            pi.setProduit(p);
        });

        return bill;

    }
}
