package org.Main.Services;

import org.Main.Enums.MarkupType;
import org.Main.Models.Buy2Get3Promotion;
import org.Main.Models.PercentagePromotion;
import org.Main.Models.Product;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private final DecimalFormat df = new DecimalFormat("#0.00");
    @Test
    public void InitializeProductListTest(){
        ProductService productService = new ProductService();
        List<Product> products = new ArrayList<>();
        Product productA = new Product("A",0.52, MarkupType.PERCENTAGE, null,80,0);
        Product productB = new Product("B",0.38, MarkupType.PERCENTAGE, new PercentagePromotion(30),120,0);
        Product productC = new Product("C",0.41, MarkupType.PER_UNIT, null,0.9,0);
        Product productD = new Product("D",0.60, MarkupType.PER_UNIT, new Buy2Get3Promotion(),1,0);
        products.add(productA);
        products.add(productB);
        products.add(productC);
        products.add(productD);

        //Test for input with 10 000 units of product B and 3 000 units of product C
        int[] productVolumes = {0,10000,0,3000};
        List<Product> initializedList = productService.initializeProductList(productVolumes,products);
        assertTrue(initializedList.contains(productB));
        assertTrue(initializedList.contains(productD));
        assertTrue(!initializedList.contains(productA));
        assertTrue(!initializedList.contains(productC));
        assertEquals(10000,initializedList.get(0).getVolume());
        assertEquals(3000,initializedList.get(1).getVolume());
        assertEquals(0.59,Double.parseDouble(df.format(initializedList.get(0).getCost())));
        assertEquals(1.07,Double.parseDouble(df.format(initializedList.get(1).getCost())));
        //Test for input with 10 000 units of product A and 20 000 units of product C
        int[] productVolumes2 = {10000,0,20000,0};
        List<Product> initializedList2 = productService.initializeProductList(productVolumes2,products);
        assertEquals(10000,initializedList2.get(0).getVolume());
        assertEquals(20000,initializedList2.get(1).getVolume());
        assertTrue(initializedList2.contains(productA));
        assertTrue(initializedList2.contains(productC));
        assertEquals(0.94,Double.parseDouble(df.format(initializedList2.get(0).getCost())));
        assertEquals(1.31,Double.parseDouble(df.format(initializedList2.get(1).getCost())));

    }
    @Test
    public void MarkupPerUnitTest(){
        ProductService productService = new ProductService();
        Product product = new Product("A",1.0, MarkupType.PER_UNIT,null,0.9,50);
        productService.figureMarkupType(product);
        assertEquals(1.90,product.getCost());
    }

    @Test void MarkupPercentageTest(){
        ProductService productService = new ProductService();
        Product productA = new Product("A",0.52, MarkupType.PERCENTAGE, null,80,0);
        Product productB = new Product("B",0.38, MarkupType.PERCENTAGE, new PercentagePromotion(30),120,0);
        productService.figureMarkupType(productA);
        productService.figureMarkupType(productB);
        assertEquals(0.936,productA.getCost());
        assertEquals(0.836,productB.getCost());
    }

    @Test void PerUnitMarkupAndPercentagePromotion(){
        ProductService productService = new ProductService();
        Product product = new Product("A",1.0, MarkupType.PER_UNIT,new PercentagePromotion(50),1,50);
        productService.figureMarkupType(product);
        product.getPromotion().calculatePromotionBasedOnType(product);
        assertEquals(1,product.getCost());
    }

    @Test void PercentageUnitMarkupAndBuy2Get3Promotion(){
        ProductService productService = new ProductService();
        Product product = new Product("A",0.2, MarkupType.PERCENTAGE,new Buy2Get3Promotion(),50,50);
        productService.figureMarkupType(product);
        assertEquals(0.3,Double.parseDouble(df.format(product.getCost())));
        product.getPromotion().calculatePromotionBasedOnType(product);
        assertEquals(0.2,Double.parseDouble(df.format(product.getCost())));
    }

    @Test void PerUnitMarkupAndBuy2Get3Promotion(){
        ProductService productService = new ProductService();
        Product product = new Product("A",0.6, MarkupType.PER_UNIT,new Buy2Get3Promotion(),1,30);
        productService.figureMarkupType(product);
        assertEquals(1.6,product.getCost());
        product.getPromotion().calculatePromotionBasedOnType(product);
        assertEquals(1.07,Double.parseDouble(df.format(product.getCost())));
    }
}