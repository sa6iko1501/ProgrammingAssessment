package org.Main.Services;

import lombok.Getter;
import lombok.Setter;
import org.Main.Enums.MarkupType;
import org.Main.Models.Product;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductService {

    private final DecimalFormat df = new DecimalFormat("#0.00");
    public List<Product> initializeProductList(int[]products, List<Product> productList){
        List<Product> productsToKeep = new ArrayList<>();
        for(int i=0;i< products.length;i++){

            //Find out which products are ordered, set their volumes and put them in a new List
           if(products[i]>0){
               productList.get(i).setVolume(products[i]);
               productsToKeep.add(productList.get(i));
           }
        }

        for(int i=0;i<productsToKeep.size();i++){

            //Do product operations for each ordered product and print out details
            System.out.println("Product: "+productsToKeep.get(i).getName());
            System.out.println("\tQuantity: "+productsToKeep.get(i).getVolume());
            System.out.println("\t"+figureMarkupType(productsToKeep.get(i)));
            if(productsToKeep.get(i).getPromotion()!=null){
                System.out.println("\t"+productsToKeep.get(i).getPromotion().calculatePromotionBasedOnType(productsToKeep.get(i)));
            }
            System.out.println("\tTotal: "+df.format(productsToKeep.get(i).getCost()*productsToKeep.get(i).getVolume())+" EUR");
            System.out.println("\n");

        }

        //Send the List of ordered products with calculated markup and promotions
        return productsToKeep;
    }
   public String figureMarkupType(Product product){

        //Will use Product.markup as a hard value or as a percentage based on Product.MarkupType
       if(product.getMarkupType()==MarkupType.PERCENTAGE){
           return "Base unit price: "+df.format(markupPercentage(product,product.getCost(), product.getMarkup()))+" EUR/unit";
       }
       if(product.getMarkupType()==MarkupType.PER_UNIT){
           return "Base unit price: "+df.format(markupPerUnit(product,product.getCost(), product.getMarkup()))  + " EUR/unit";
       }
       return null;
   }

    private double markupPercentage(Product product,double cost,double percent){

        //Calculate cost with the markup percentage and change it in the product
        double percentForCalc = (double)percent/100.0;
        percentForCalc = Double.parseDouble(df.format(percentForCalc));
        double newCost = cost+(cost*percentForCalc);
        product.setCost(newCost);
        return newCost;
    }

    private double markupPerUnit(Product product,double cost,double perUnit){

        //Calculate cost with the markup per unit and change it in the product
        double newCost = (cost + perUnit);
        product.setCost(newCost);
        return newCost;
    }


}
