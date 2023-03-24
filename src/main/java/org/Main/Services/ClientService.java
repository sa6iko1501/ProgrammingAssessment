package org.Main.Services;


import org.Main.Models.Client;

import org.Main.Models.Product;

import java.util.List;

public class ClientService {
    private Client client;
    private final ProductService productService = new ProductService();
    public void initiateClient(Client client, int[] products, List<Product> productList){
        this.client = client;
        this.client.setProducts(productService.initializeProductList(products,productList));
        calculateClientDiscount(this.client.getBasicClientDiscount());

    }

    public void calculateClientDiscount(double percentage){
        if(percentage!=0){
            percentage = percentage/100;
            client.setBasicClientDiscount(percentage);
        }
        else{
            client.setBasicClientDiscount(0);
        }
    }
    public double calculateVolumeDiscount(double percentage){
        if(percentage!=0){
            percentage = percentage/100;
            return percentage;
        }
        else{
            return 0;
        }
    }

}
