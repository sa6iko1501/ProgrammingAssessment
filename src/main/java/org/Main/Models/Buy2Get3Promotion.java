package org.Main.Models;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class Buy2Get3Promotion implements Promotion{


    @Override
    public String calculatePromotionBasedOnType(Product product) {
        //Assuming volume of order is set with the promotion in mind
        int volume = product.getVolume();
        double cost = product.getCost();
        DecimalFormat df = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US));
        int numFullTrios = volume / 3;
        int numIndividuals = volume % 3;
        double discountedCost = cost * 2.0;
        double totalCost = numFullTrios * discountedCost + numIndividuals * cost;
        double averageCost = totalCost / volume;
        product.setCost(averageCost);
        averageCost = Double.parseDouble(df.format(averageCost));
        return "Promotion Buy 2 Get 3rd for free: "+df.format(averageCost)+" EUR/unit on average";

    }
}
