package org.Main.Services;

import org.Main.Enums.MarkupType;
import org.Main.Models.Buy2Get3Promotion;
import org.Main.Models.Client;
import org.Main.Models.PercentagePromotion;
import org.Main.Models.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    @Test
    public void calculateVolumeDiscountTest(){
        ClientService clientService = new ClientService();
        assertEquals(0.05,clientService.calculateVolumeDiscount(5));
        assertEquals(0.99,clientService.calculateVolumeDiscount(99));
        //Percent > 99
        assertThrows(IllegalArgumentException.class,
                ()->{
                    clientService.calculateVolumeDiscount(99.01);
                });
        //Percent Edge Max
        assertThrows(IllegalArgumentException.class,
                ()->{
                    clientService.calculateVolumeDiscount(Double.MAX_VALUE);
                });
        //Percent <0
        assertThrows(IllegalArgumentException.class,
                ()->{
                    clientService.calculateVolumeDiscount(-0.01);
                });
        //Percent Edge Min
        assertThrows(IllegalArgumentException.class,
                ()->{
                    clientService.calculateVolumeDiscount(-2.225E-307);
                });
    }
    @Test
    public void calculateClientDiscountTest(){
        ClientService clientService = new ClientService();
        Client client = new Client(1,4,0,2,null);
        assertEquals(0.04,clientService.calculateClientDiscount(client.getBasicClientDiscount()));
        client.setBasicClientDiscount(0);
        assertEquals(0,clientService.calculateClientDiscount(client.getBasicClientDiscount()));
        //Percent > 99
        assertThrows(IllegalArgumentException.class,
                ()->{
                    clientService.calculateClientDiscount(99.01);
                });
        //Percent Edge Max
        assertThrows(IllegalArgumentException.class,
                ()-> clientService.calculateClientDiscount(Double.MAX_VALUE));
        //Percent <0
        assertThrows(IllegalArgumentException.class,
                ()->{
                    clientService.calculateClientDiscount(-0.01);
                });
        //Percent Edge Min
        assertThrows(IllegalArgumentException.class,
                ()->{
                    clientService.calculateClientDiscount(-2.225E-307);
                });
    }
    @Test
    public void calculateTotalTest(){
        ClientService clientService = new ClientService();
        List<Product> products = new ArrayList<>();
        Product productA = new Product("A",0.52, MarkupType.PERCENTAGE, null,80,100);
        Product productB = new Product("B",0.38, MarkupType.PERCENTAGE, new PercentagePromotion(30),120,67);
        Product productC = new Product("C",0.41, MarkupType.PER_UNIT, null,0.9,21);
        Product productD = new Product("D",0.60, MarkupType.PER_UNIT, new Buy2Get3Promotion(),1,5023);
        products.add(productA);
        products.add(productB);
        products.add(productC);
        products.add(productD);
        assertEquals(3099.87,clientService.calculateTotal(products));
    }

    @Test
    public void initializeClientTest(){}
}