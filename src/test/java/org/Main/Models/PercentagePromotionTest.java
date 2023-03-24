package org.Main.Models;

import org.Main.Enums.MarkupType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentagePromotionTest {

    @Test
    public void onePercent(){
        PercentagePromotion percentagePromotion = new PercentagePromotion(1);
        Product product = new Product("A",1.0, MarkupType.PER_UNIT,percentagePromotion,0.9,50);
        assertEquals("Promotion -1% off : 0.99 EUR/unit",product.getPromotion().calculatePromotionBasedOnType(product));
    }
    @Test
    public void thirtyPercent(){
        PercentagePromotion percentagePromotion = new PercentagePromotion(30);
        Product product = new Product("A",1.0, MarkupType.PER_UNIT,percentagePromotion,0.9,50);
        assertEquals("Promotion -30% off : 0.70 EUR/unit",product.getPromotion().calculatePromotionBasedOnType(product));
    }

    @Test
    public void fiftyPercent(){
        PercentagePromotion percentagePromotion = new PercentagePromotion(50);
        Product product = new Product("A",1.0, MarkupType.PER_UNIT,percentagePromotion,0.9,50);
        assertEquals("Promotion -50% off : 0.50 EUR/unit",product.getPromotion().calculatePromotionBasedOnType(product));
    }
    @Test
    public void ninetyNine(){
        PercentagePromotion percentagePromotion = new PercentagePromotion(99);
        Product product = new Product("A",1.0, MarkupType.PER_UNIT,percentagePromotion,0.9,50);
        assertEquals("Promotion -99% off : 0.01 EUR/unit",product.getPromotion().calculatePromotionBasedOnType(product));
    }

    @Test
    public void negativePercentage(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new PercentagePromotion(-5);
                });
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new PercentagePromotion(0);
                });
    }

    @Test
    public void percentageHigherOrEqualTo100(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new PercentagePromotion(100);
                });
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new PercentagePromotion(Integer.MAX_VALUE);
                });
    }

    @Test
    public void setPercentageToNegativeOr0(){
        PercentagePromotion percentagePromotion = new PercentagePromotion(30);
        assertThrows(IllegalArgumentException.class,
                ()->{
                    percentagePromotion.setPercentage(0);
                });
        assertThrows(IllegalArgumentException.class,
                ()->{
                    percentagePromotion.setPercentage(-Integer.MIN_VALUE);
                });
    }

    @Test
    public void getPercentage(){
        PercentagePromotion percentagePromotion = new PercentagePromotion(30);
        assertEquals(30,percentagePromotion.getPercentage());
    }
}