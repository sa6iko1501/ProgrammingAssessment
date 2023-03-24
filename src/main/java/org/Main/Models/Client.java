package org.Main.Models;
import lombok.Getter;
import lombok.Setter;
import java.util.List;



@Getter
@Setter
public class Client {
    private int id;

    private double basicClientDiscount;
    private double volumeDiscount10000;

    private double volumeDiscount30000;
    private List<Product> products;


    public void addProduct(Product product){
        this.products.add(product);
    }

    public Client(int id, double basicClientDiscount, double volumeDiscount10000, double volumeDiscount30000,List<Product> products){
        if(basicClientDiscount<0){
            throw new IllegalArgumentException("Client discount cannot be lower than 0");
        }
        if(basicClientDiscount>99.9){
            throw new IllegalArgumentException("Client discount cannot be higher than 99.9");
        }
        if(volumeDiscount10000<0){
            throw new IllegalArgumentException("Volume discount cannot lower than 0");
        }
        if(volumeDiscount10000>99.9){
            throw new IllegalArgumentException("Volume discount cannot higher than 99.9");
        }

        if(volumeDiscount30000<0){
            throw new IllegalArgumentException("Volume discount cannot be lower than 0");
        }
        if(volumeDiscount30000>99.9){
            throw new IllegalArgumentException("Volume discount cannot be higher than 99.9");
        }
        this.id = id;
        this.basicClientDiscount = basicClientDiscount;
        this.volumeDiscount10000 = volumeDiscount10000;
        this.volumeDiscount30000 = volumeDiscount30000;
        this.products = products;
    }
    public void setBasicClientDiscount(double basicClientDiscount) {
        if(basicClientDiscount<0){
            throw new IllegalArgumentException("Client discount cannot be set lower than 0");
        }
        if(basicClientDiscount>99.9){
            throw new IllegalArgumentException("Client discount cannot be set higher than 99.9");
        }
        this.basicClientDiscount = basicClientDiscount;
    }

    public void setVolumeDiscount10000(double volumeDiscount10000) {
        if(volumeDiscount10000<0){
            throw new IllegalArgumentException("Volume discount cannot be set lower than 0");
        }
        if(volumeDiscount10000>99.9){
            throw new IllegalArgumentException("Volume discount cannot be set higher than 99.9");
        }
        this.volumeDiscount10000 = volumeDiscount10000;
    }

    public void setVolumeDiscount30000(double volumeDiscount30000) {
        if(volumeDiscount30000<0){
            throw new IllegalArgumentException("Volume discount cannot be set lower than 0");
        }
        if(volumeDiscount30000>99.9){
            throw new IllegalArgumentException("Volume discount cannot be set higher than 99.9");
        }
        this.volumeDiscount30000 = volumeDiscount30000;
    }
}
