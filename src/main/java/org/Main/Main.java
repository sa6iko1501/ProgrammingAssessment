package org.Main;

import org.Main.Enums.MarkupType;
import org.Main.Models.*;
import org.Main.Services.ClientService;
import org.Main.Services.ValidationService;

import java.util.*;

public class Main {

    private static final ClientService clientService = new ClientService();
    private static final ValidationService validationService = new ValidationService();
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
       while(true) {
           //Greetings print and instantiate objects and lists
           System.out.println("\nPlease enter a single line of input with syntax: " +
                   "[clientId] [volumeOfOrderProduct1] ... [volumeOfOrderProductN]\n");
           Parser parser = new Parser();
           List<Client> clientList = initiateClients();
           List<Product> products = initiateProducts();

           //Get input, split it and add it to a List<String>
           String firstLine = scanner.nextLine();
           String[] firstInput = firstLine.split(" ");
           List<String> input = new ArrayList<>();
           input.addAll(Arrays.asList(firstInput));

           //Check whether input is all Int values and whether it's
           //the correct size
           if(validationService.validateInput(clientList,products,input)){

               //If input is valid parse first element of list to int
               // and remove it from the list
               //If no user with the input id is found parser will return 0
               int clientId = parser.parseClientId(input);
               if(clientId!=0){

                   //If client with the input id exists create instance of client
                   //Will return null if client is not found in the list but should
                   //be impossible after passing validateInput()
                   Client client = selectClientFromClientList(clientList,clientId);

                   //Parse the rest of the inputs
                   //If parsing fails for some reason parser will return null
                   int[] productVolumes = parser.parseInput(input);
                   if(productVolumes!=null){

                       //call clientService.finalizeOrder
                       clientService.finalizeOrder(client,productVolumes,products);
                   }
               }
           }
       }
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

    private static List<Client> initiateClients(){
        //To initiate the clients and play around with values
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1,5,0,2,null));
        clients.add(new Client(2,4,1,2,null));
        clients.add(new Client(3,3,1,3,null));
        clients.add(new Client(4,2,3,5,null));
        clients.add(new Client(5,0,5,7,null));
        return clients;
    }

    private static Client  selectClientFromClientList(List<Client> clients, int id){
        //Finds the client with the input id from the list of clients
        for(int i=0;i< clients.size();i++){
            if(clients.get(i).getId()==id){
                return clients.get(i);
            }
        }
        return null;
    }
}
