package org.Main.Services;

import org.Main.Models.Client;
import org.Main.Models.Product;
import java.text.DecimalFormat;
import java.util.List;

public class ClientService {
    private Client client;
    private final ProductService productService = new ProductService();
    private final DecimalFormat df = new DecimalFormat("#0.00");
    public void initiateClient(Client client, int[] products, List<Product> productList){
        double total;
        this.client = client;
        this.client.setProducts(productService.initializeProductList(products,productList));

        //Calculate total after product promotions have been applied
        total = calculateTotal(this.client.getProducts());
        System.out.println("Total Price Before Client Discounts: "+df.format(total)+" EUR");

        //Check if the client has a basic client discount
        if(client.getBasicClientDiscount()!=0){

            //Print the discount percentage and recalculate the total after the discount
            double percentBeforeChange = client.getBasicClientDiscount();
            this.client.setBasicClientDiscount(calculateClientDiscount(client.getBasicClientDiscount()));
            total = applyClientDiscount(this.client,total);
            System.out.println("Basic Client Discount - "+df.format(percentBeforeChange)+
                    "% Total: "+df.format(total));
        }
        //Check if Volume discount is applicable More checks in applyVolumeDiscount()
        if(client.getVolumeDiscount10000()!=0){

            //This will turn the percentage into something we can multiply with ex. 25% -> 0.25
            this.client.setVolumeDiscount10000(calculateVolumeDiscount(client.getVolumeDiscount10000()));

            //This will return the same total if no product.volume was higher or equal than 10 000
            //and will print and recalculate the total if we have applied a promotion
            total = applyVolumeDiscount(this.client,this.client.getProducts(),total);
        }
        if(client.getVolumeDiscount30000()!=0){

            //This will turn the percentage into something we can multiply with ex. 25% -> 0.25
            this.client.setVolumeDiscount30000(calculateVolumeDiscount(client.getVolumeDiscount30000()));

            //This will return the same total if no product.volume was higher or equal than 30 000
            //and will print and recalculate the total if we have applied a promotion
            total = applyVolumeDiscount(this.client,this.client.getProducts(),total);
        }
        System.out.println("Order Total: "+df.format(total)+" EUR");

    }
    public double calculateTotal(List<Product> productList){
        double total = 0;
        for(int i=0;i<productList.size();i++){
            total = total + productList.get(i).getCost()*productList.get(i).getVolume();
        }
        return Double.parseDouble(df.format(total));
    }
    public double calculateClientDiscount(double percentage){
        if(percentage>99.0){
            throw new IllegalArgumentException("Percentage of Client Discount cannot be more than 100");
        }
        if(percentage<0.0){
            throw new IllegalArgumentException("Percentage of Client Discount cannot be less than 0");
        }
        if(percentage!=0){
            double finalClientDiscount;
            finalClientDiscount = percentage/100;
            return Double.parseDouble(df.format(finalClientDiscount));
        }
        else{
            return 0.0;
        }
    }

    public double calculateVolumeDiscount(double percentage){
        if(percentage>99.0){
            throw new IllegalArgumentException("Percentage of Volume Discount cannot be more than 100");
        }
        if(percentage<0.0){
            throw new IllegalArgumentException("Percentage of Volume Discount cannot be less than 0");
        }
        if(percentage!=0){
            double finalVolumeDiscount;
            finalVolumeDiscount = percentage/100;
            return Double.parseDouble(df.format(finalVolumeDiscount));
        }
        else{
            return 0.0;
        }
    }
    public double applyClientDiscount(Client client, double total){
        double newTotal = total - total*client.getBasicClientDiscount();
        return Double.parseDouble(df.format(newTotal));
    }

    public double applyVolumeDiscount(Client client, List<Product> products,double total){
        double newTotal = 0;
        boolean flag = false;
        for(int i=0;i<products.size();i++){

            //Check if >10 000 volume promotion is applicable
            if(products.get(i).getVolume()>=10000 && products.get(i).getVolume()<30000 && client.getVolumeDiscount10000()!=0){
                products.get(i).setCost(Double.parseDouble(df.format(products.get(i).getCost()*client.getVolumeDiscount10000())));

                //Print if promotion is applied
                System.out.println("Volume discount over 10 000 applied to product '"+
                        products.get(i).getName()+"'");
                flag = true;
            }

            //Check if >30 000 volume promotion is applicable
            if(products.get(i).getVolume()>=30000 && client.getVolumeDiscount30000()!=0){
                products.get(i).setCost(Double.parseDouble(df.format(products.get(i).getCost()*client.getVolumeDiscount30000())));

                //Print if promotion is applied
                System.out.println("Volume discount over 30 000 applied to product '"+
                        products.get(i).getName()+"'");
                flag = true;
            }
        }
        //Check if we have applied any promotions and need to recalculate total
        if(flag){
            newTotal = calculateTotal(products);
            return newTotal;
        }
        else{
            return total;
        }
    }
}
