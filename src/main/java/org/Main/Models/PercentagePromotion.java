package org.Main.Models;


import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


@Getter
@Setter
public class PercentagePromotion implements Promotion{


    private int percentage;

    public PercentagePromotion(int percentage){
        if(percentage<=0){
            throw new IllegalArgumentException("Promotion percentage cannot be set to a negative value or 0");
        }
        if(percentage>99){
            throw new IllegalArgumentException("Promotion percentage cannot be set to a higher value than 99");
        }
        this.percentage=percentage;
    }
    public void setPercentage(int percentage) {
        if(percentage<=0){
            throw new IllegalArgumentException("Promotion percentage cannot be set to a negative value or 0");
        }
        if(percentage>=100){
            throw new IllegalArgumentException("Promotion percentage cannot be set to  higher value than 99");
        }
        this.percentage = percentage;
    }

    @Override
    public String calculatePromotionBasedOnType(Product product) {
        DecimalFormat df = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.US));
        double cost = product.getCost();
        double discountedCost=cost-(cost*percentage/100.0);
        product.setCost(discountedCost);
        discountedCost = Double.parseDouble(df.format(discountedCost));

        return "Promotion -"+percentage+"% off : "+df.format(discountedCost)+" EUR/unit";
    }
}
