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

