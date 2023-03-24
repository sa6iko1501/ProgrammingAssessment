package org.Main.Models;

import org.Main.Enums.MarkupType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Buy2Get3PromotionTest {

    @Test
    void TwentyOne() {
        Buy2Get3Promotion buy2Get3Promotion = new Buy2Get3Promotion();
        Product product = new Product("A",0.30, MarkupType.PER_UNIT,buy2Get3Promotion,0.9,21);
        assertEquals("Promotion Buy 2 Get 3rd for free: 0.20 EUR/unit on average",product.getPromotion().calculatePromotionBasedOnType(product));
        assertEquals(0.20,product.getCost());
    }

    @Test
    void TwentyTwo(){
        Buy2Get3Promotion buy2Get3Promotion = new Buy2Get3Promotion();
        Product product = new Product("A",0.30, MarkupType.PER_UNIT,buy2Get3Promotion,0.9,22);
        assertEquals("Promotion Buy 2 Get 3rd for free: 0.20 EUR/unit on average",product.getPromotion().calculatePromotionBasedOnType(product));
        assertEquals(0.2,product.getCost());
    }

    @Test
    void TwentyThree(){
        Buy2Get3Promotion buy2Get3Promotion = new Buy2Get3Promotion();
        Product product = new Product("A",0.30, MarkupType.PER_UNIT,buy2Get3Promotion,0.9,23);
        assertEquals("Promotion Buy 2 Get 3rd for free: 0.21 EUR/unit on average",product.getPromotion().calculatePromotionBasedOnType(product));
        assertEquals(0.21,product.getCost());
    }



}