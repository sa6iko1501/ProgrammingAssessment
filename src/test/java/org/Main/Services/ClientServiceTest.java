package org.Main.Services;

import org.Main.Enums.MarkupType;
import org.Main.Models.Buy2Get3Promotion;
import org.Main.Models.Client;
import org.Main.Models.PercentagePromotion;
import org.Main.Models.Product;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private final DecimalFormat df = new DecimalFormat("#0.00");
    @Test
    public void calculateVolumeDiscountTest(){
        ClientService clientService = new ClientService();
        assertEquals(0.05,clientService.calculateVolumeDiscount(5));
        assertEquals(0.99,clientService.calculateVolumeDiscount(99));
        //Percent > 99
        assertThrows(IllegalArgumentException.class,
                ()-> clientService.calculateVolumeDiscount(99.01));
        //Percent Edge Max
        assertThrows(IllegalArgumentException.class,
                ()-> clientService.calculateVolumeDiscount(Double.MAX_VALUE));
        //Percent <0
        assertThrows(IllegalArgumentException.class,
                ()-> clientService.calculateVolumeDiscount(-0.01));
        //Percent Edge Min
        assertThrows(IllegalArgumentException.class,
                ()-> clientService.calculateVolumeDiscount(-2.225E-307));
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
                ()-> clientService.calculateClientDiscount(99.01));
        //Percent Edge Max
        assertThrows(IllegalArgumentException.class,
                ()-> clientService.calculateClientDiscount(Double.MAX_VALUE));
        //Percent <0
        assertThrows(IllegalArgumentException.class,
                ()-> clientService.calculateClientDiscount(-0.01));
        //Percent Edge Min
        assertThrows(IllegalArgumentException.class,
                ()-> clientService.calculateClientDiscount(-2.225E-307));
    }

    @Test
    public void calculateTotalTest(){
        ClientService clientService = new ClientService();
        List<Product> products = new ArrayList<>();
        Product productA = new Product("A",0.936, MarkupType.PERCENTAGE, null,80,10000);
        Product productB = new Product("B",0.38, MarkupType.PERCENTAGE, new PercentagePromotion(30),120,0);
        Product productC = new Product("C",1.31, MarkupType.PER_UNIT, null,0.9,20000);
        Product productD = new Product("D",0.60, MarkupType.PER_UNIT, new Buy2Get3Promotion(),1,0);
        //Product A and C test
        products.add(productA);
        products.add(productC);
        assertEquals(35560,clientService.calculateTotal(products));
        //Product B and D test
    }

    @Test
    public void initializeClientTest(){}

    @Test
    public void applyVolumeDiscountTest(){
        ClientService clientService = new ClientService();
        Client client = new Client(1,4,0.05,0.1,null);
        //Test that the correct discount is being applied
        assertEquals(9500,clientService.applyVolumeDiscount(client,10000));
        assertEquals(19000,clientService.applyVolumeDiscount(client,20000));
        assertEquals(27000,clientService.applyVolumeDiscount(client,30000));
        assertEquals(54000,clientService.applyVolumeDiscount(client,60000));
        assertEquals(5000,clientService.applyVolumeDiscount(client,5000));
        //Total = 0
        assertThrows(IllegalArgumentException.class,
                ()->clientService.applyVolumeDiscount(client,0)
                );
        //Negative Edge case
        assertThrows(IllegalArgumentException.class,
                ()->clientService.applyVolumeDiscount(client,-2.225E-307)
                );
    }

    @Test
    public void applyClientDiscountTest(){
        ClientService clientService = new ClientService();
        Client client = new Client(1,3,0.05,0.10,null);
        client.setBasicClientDiscount(clientService.calculateClientDiscount(client.getBasicClientDiscount()));
        assertEquals(12059.93,Double.parseDouble(df.format(clientService.applyClientDiscount(client,12432.92))));
        //Total = 0
        assertThrows(IllegalArgumentException.class,
                ()->clientService.applyClientDiscount(client,0)
                );
        //Total negative edge case
        assertThrows(IllegalArgumentException.class,
                ()->clientService.applyClientDiscount(client,-Double.MAX_VALUE)
        );
    }

    @Test
    public void finalizeOrderTest(){
        //Test Case given in the Assessment Task
        Client client = new Client(5,0,5,7,null);
        ClientService clientService = new ClientService();
        List<Product> products = new ArrayList<>();
        Product productA = new Product("A",0.52, MarkupType.PERCENTAGE, null,80,0);
        Product productB = new Product("B",0.38, MarkupType.PERCENTAGE, new PercentagePromotion(30),120,0);
        Product productC = new Product("C",0.41, MarkupType.PER_UNIT, null,0.9,0);
        Product productD = new Product("D",0.60, MarkupType.PER_UNIT, new Buy2Get3Promotion(),1,0);
        products.add(productA);
        products.add(productB);
        products.add(productC);
        products.add(productD);
        int[] productVolumes = {10000,0,20000,0};
        assertEquals(33070.8,clientService.finalizeOrder(client,productVolumes,products));
    }

    @Test void finalizeOrderTestWithAllProductsOrderedAndAllPromotionsApplied(){
        //Test Case given in the Assessment Task
        Client client = new Client(5,10,5,10,null);
        ClientService clientService = new ClientService();
        List<Product> products = new ArrayList<>();
        Product productA = new Product("A",0.52, MarkupType.PERCENTAGE, null,80,0);
        Product productB = new Product("B",0.38, MarkupType.PERCENTAGE, new PercentagePromotion(30),120,0);
        Product productC = new Product("C",0.41, MarkupType.PER_UNIT, null,0.9,0);
        Product productD = new Product("D",0.60, MarkupType.PER_UNIT, new Buy2Get3Promotion(),1,0);
        products.add(productA);
        products.add(productB);
        products.add(productC);
        products.add(productD);
        int[] productVolumes = {10000,10000,10000,10000};
        assertEquals(31573.15, Double.parseDouble(df.format(clientService.finalizeOrder(client,productVolumes,products))));
    }
}

