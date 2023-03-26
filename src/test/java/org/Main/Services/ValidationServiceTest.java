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

class ValidationServiceTest {

    @Test
    public void validateClientInputTest(){
        ValidationService validationService = new ValidationService();
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1,5,0,2,null));
        clients.add(new Client(2,4,1,2,null));
        clients.add(new Client(3,3,1,3,null));
        clients.add(new Client(4,2,3,5,null));
        clients.add(new Client(5,0,5,7,null));
        List<Product> products = new ArrayList<>();
        products.add(new Product("A",0.52, MarkupType.PERCENTAGE, null,80,0));
        products.add(new Product("B",0.38, MarkupType.PERCENTAGE, new PercentagePromotion(30),120,0));
        products.add(new Product("C",0.41, MarkupType.PER_UNIT, null,0.9,0));
        products.add(new Product("D",0.60, MarkupType.PER_UNIT, new Buy2Get3Promotion(),1,0));
        List<String> input = new ArrayList<>();
        input.add("5");
        input.add("10000");
        input.add("0");
        input.add("200000");
        input.add("0");
        //Valid Input
        assertTrue(validationService.validateInput(clients,products,input));

        //No client with this id
        input.set(0,"6");
        assertFalse(validationService.validateInput(clients,products,input));

        //Input contains a negative value
        input.set(0,"-1");
        assertFalse(validationService.validateInput(clients,products,input));

        //Input contains a variable that cannot be parsed to Int
        input.set(0,"asd");
        input.set(4,"1.32");
        assertFalse(validationService.validateInput(clients,products,input));

        //Input is too small
        input.remove(0);
        assertFalse(validationService.validateInput(clients,products,input));

        //Input is too big
        input.add("5");
        input.add("5");
        assertFalse(validationService.validateInput(clients,products,input));
    }
}