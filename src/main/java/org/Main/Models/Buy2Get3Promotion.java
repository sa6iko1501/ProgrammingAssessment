package org.Main.Models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Math.round;

public class Buy2Get3Promotion implements Promotion{

    private static final DecimalFormat dcimalFormat = new DecimalFormat("0.00");
    @Override
    public String calculatePromotionBasedOnType(Product product) {
        //Assuming volume of order is set with the promotion in mind
        int volume = product.getVolume();
        double cost = product.getCost();
        DecimalFormat df = new DecimalFormat("#0.00");
        int numFullTrios = volume / 3;
        int numIndividuals = volume % 3;
        double discountedCost = cost * 2.0;
        double totalCost = numFullTrios * discountedCost + numIndividuals * cost;
        double averageCost = totalCost / volume;
        averageCost = Double.parseDouble(df.format(averageCost));
        product.setCost(averageCost);
        return "Promotion Buy 2 Get 3rd for free: "+df.format(averageCost)+" EUR/unit on average";

    }
}
