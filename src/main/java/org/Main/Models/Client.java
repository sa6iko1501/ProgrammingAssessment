package org.Main.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class Client {
    private int id;

    private double basicClientDiscount;
    private double volumeDiscount10000;

    private double volumeDiscount30000;
    private List<Product> products;

    public Client(int id){
        this.id =id;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}
