package org.Main.Models;

import org.Main.Enums.MarkupType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void createProduct(){
        Product product = new Product("Beer",0.78, MarkupType.PER_UNIT,new PercentagePromotion(25),20,50);
        assertEquals("Beer",product.getName());
        assertEquals(0.78,product.getCost());
        assertEquals(MarkupType.PER_UNIT,product.getMarkupType());
        assertEquals(PercentagePromotion.class,product.getPromotion().getClass());
        assertEquals(20,product.getMarkup());
        assertEquals(50,product.getVolume());
    }

    @Test
    public void ExceptionsFromConstructor(){
        //Volume <0
        assertThrows(IllegalArgumentException.class,
                ()-> {
                    new Product("Beer",0.78,MarkupType.PER_UNIT,null,20,-1);
                });
        //Volume Negative Edge
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new Product("Beer",0.78,MarkupType.PER_UNIT,null,20,Integer.MIN_VALUE);
                });
        //Markup = 0
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new Product("Beer",0.78,MarkupType.PER_UNIT,null,0,50);
                });
        //Markup Negative Edge
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new Product("Beer",0.78,MarkupType.PER_UNIT,null,Integer.MIN_VALUE,50);
                });
        //Cost = 0
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new Product("Beer",0,MarkupType.PER_UNIT,null,0,50);
                });
        //Cost Negative Edge
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new Product("Beer",Integer.MIN_VALUE,MarkupType.PER_UNIT,null,0,50);
                });
        //MarkupType = NULL
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new Product("Beer",0.78,null,null,0,50);
                });
        //Name is null
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new Product(null,0.78,MarkupType.PER_UNIT,null,0,50);
                });
        //Name is Empty
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new Product("",0.78,MarkupType.PER_UNIT,null,0,50);
                });
    }

    @Test
    public void setVolumeTests(){
        Product product =  new Product("Beer",0.78,MarkupType.PER_UNIT,null,0.3,50);
        assertThrows(IllegalArgumentException.class,
                ()->{
                    product.setVolume(-1);
                });
        assertThrows(IllegalArgumentException.class,
                ()->{
                    product.setVolume(Integer.MIN_VALUE);
                });
    }

    @Test
    public void setCostTests(){
        Product product =  new Product("Beer",0.78,MarkupType.PER_UNIT,null,0.3,50);
        //Cost = 0
        assertThrows(IllegalArgumentException.class,
                ()->{
                    product.setCost(0);
                });
        //Cost Negative Edge
        assertThrows(IllegalArgumentException.class,
                ()->{
                    product.setCost(Integer.MIN_VALUE);
                });
    }
    @Test
    public void setMarkupTests(){
        Product product =  new Product("Beer",0.78,MarkupType.PER_UNIT,null,0.3,50);
        //Markup = 0
        assertThrows(IllegalArgumentException.class,
                ()->{
                    product.setMarkup(0);
                });
        //Markup  Negative Edge
        assertThrows(IllegalArgumentException.class,
                ()->{
                    product.setMarkup(Integer.MIN_VALUE);
                });
    }

    @Test
    public void setMarkupType(){
        //MarkupType = null
        Product product =  new Product("Beer",0.78,MarkupType.PER_UNIT,null,0.3,50);
        assertThrows(IllegalArgumentException.class,
                ()->{
                    product.setMarkupType(null);
                });
    }
}