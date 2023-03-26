package org.Main.Services;

import org.Main.Models.Client;
import org.Main.Models.Product;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class ClientService {
    private final ProductService productService = new ProductService();
    private final DecimalFormat df = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US));
    public double finalizeOrder(Client client, int[] products, List<Product> productList){
        double total;
        client.setProducts(productService.initializeProductList(products,productList));

        //Calculate total after product promotions have been applied
        total = calculateTotal(client.getProducts());
        System.out.println("Total Price Before Client Discounts: "+df.format(total)+" EUR");

        //Check if the client has a basic client discount
        if(client.getBasicClientDiscount()!=0){

            //Print the discount percentage and recalculate the total after the discount
            int oldPercent = (int)client.getBasicClientDiscount();
            client.setBasicClientDiscount(calculateClientDiscount(client.getBasicClientDiscount()));
            total = applyClientDiscount(client,total);
            System.out.println("\tBasic Client Discount - "+oldPercent+
                    "% Total: "+df.format(total)+" EUR");
        }

        //Check to see if the client and order qualify for any volume promotions
        if(total>=10000 && client.getVolumeDiscount10000()!=0 && total<30000){
            int oldPercent = (int)client.getVolumeDiscount10000();
            client.setVolumeDiscount10000(calculateVolumeDiscount(oldPercent));
            total = applyVolumeDiscount(client,total);
            System.out.println("\tApplied Volume Discount for orders over 10 000 EUR : -"+oldPercent+"%");
        }
        else{
            if(total>30000 && client.getVolumeDiscount30000()!=0){
                int oldPercent = (int)client.getVolumeDiscount30000();
                client.setVolumeDiscount30000(calculateVolumeDiscount(oldPercent));
                total = applyVolumeDiscount(client,total);
                System.out.println("\tApplied Volume Discount for orders over 30 000 EUR : -"+oldPercent+"%");
            }
        }
        //Print the Total price of the order after all applicable client and product promotions have been applied
        System.out.println("Order Total: "+df.format(total)+" EUR");
        return total;
    }
    private double calculateTotal(List<Product> productList){
        //Calculates the Total price of the order
        double total = 0;
        for(int i=0;i<productList.size();i++){
            total = total + productList.get(i).getCost()*productList.get(i).getVolume();
        }
        return Double.parseDouble(df.format(total));
    }
    private double calculateClientDiscount(double percentage){
        //Converts the basic client percentage ex. 5 -> 0.05; 25->0.25
        if(percentage>99.0){
            throw new IllegalArgumentException("Percentage of Client Discount cannot be more than 100");
        }
        if(percentage<0.0){
            throw new IllegalArgumentException("Percentage of Client Discount cannot be less than 0");
        }
        if(percentage!=0){
            double finalClientDiscount;
            finalClientDiscount = percentage/100;
            return finalClientDiscount;
        }
        else{
            return 0.0;
        }
    }

    private double calculateVolumeDiscount(double percentage){

        //Converts the volume discount ex. 5->0.05; 25->0.25
        if(percentage>99.0){
            throw new IllegalArgumentException("Percentage of Volume Discount cannot be more than 100");
        }
        if(percentage<0.0){
            throw new IllegalArgumentException("Percentage of Volume Discount cannot be less than 0");
        }
        if(percentage!=0){
            double finalVolumeDiscount;
            finalVolumeDiscount = percentage/100;
            return finalVolumeDiscount;
        }
        else{
            return 0.0;
        }
    }
    private double applyClientDiscount(Client client, double total){
        if(total<=0){
            throw new IllegalArgumentException("total cannot be lower or equal to 0");
        }
        //Applies the Client discount and returns the new Total
        double newTotal = total - (total*client.getBasicClientDiscount());
        return newTotal;
    }

    private double applyVolumeDiscount(Client client, double total){
        if(total<=0){
            throw new IllegalArgumentException("total cannot be lower or equal to 0");
        }
        //Checks if any volume discount is applicable and applies it if it is
        //Returns the same value for the total if no volume discount was applicable
        if(total>=10000 && total<30000){

            double newTotal = total - (total*client.getVolumeDiscount10000());
            return newTotal;
        }
        if(total>=30000){
            double newTotal = total - (total*client.getVolumeDiscount30000());
            return newTotal;
        }
        return total;
    }
}
