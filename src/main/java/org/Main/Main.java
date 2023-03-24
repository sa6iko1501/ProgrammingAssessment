package org.Main;

import org.Main.Enums.MarkupType;
import org.Main.Models.Buy2Get3Promotion;
import org.Main.Models.Client;
import org.Main.Models.PercentagePromotion;
import org.Main.Models.Product;
import org.Main.Services.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ClientService clientService = new ClientService();


    private static List<Client> initiateClients(int id){
        //To initiate the clients and play around with values
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1,5,0,2,null));
        clients.add(new Client(2,4,1,2,null));
        clients.add(new Client(3,3,1,3,null));
        clients.add(new Client(4,2,3,5,null));
        clients.add(new Client(5,0,5,7,null));
        return clients;
    }

    private static List<Product> initiateProducts(){
        //To initiate the products and play around with values
        List<Product> products = new ArrayList<>();
        products.add(new Product("A",0.52, MarkupType.PERCENTAGE, null,80,0));
        products.add(new Product("B",0.38, MarkupType.PERCENTAGE, new PercentagePromotion(30),120,0));
        products.add(new Product("C",0.41, MarkupType.PER_UNIT, null,0.9,0));
        products.add(new Product("D",0.60, MarkupType.PER_UNIT, new Buy2Get3Promotion(),1,0));
        return products;
    }
    public static void main(String[] args) {
        int client_id = sc.nextInt();
        int product1 = sc.nextInt();
        int product2 = sc.nextInt();
        int product3 = sc.nextInt();
        int product4 = sc.nextInt();

    }
}