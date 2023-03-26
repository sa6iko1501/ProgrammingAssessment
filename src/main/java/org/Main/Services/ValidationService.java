package org.Main.Services;

import org.Main.Models.Client;
import org.Main.Models.Product;

import java.util.List;

public class ValidationService {

    public boolean validateInput(List<Client> clients, List<Product> products, List<String> input){
        //Calls all validation methods
        if(!checkInputSize(products, input)){
            return false;
        }
        if(!checkInputListForParsing(input)){
            return false;
        }
        if(!checkCustomerId(clients,Integer.parseInt(input.get(0)))){
            return false;
        }
        return true;
    }
    private boolean checkCustomerId(List<Client> clients, int id){

        //Returns true if a client with the id exists
        if(clients==null){
            throw new RuntimeException("clients cannot be null");
        }
        boolean flag = false;
        for(Client client:clients){
            if(client.getId()==id){
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println("No client with id: "+id);
        }
        return flag;
    }
    private boolean checkInputSize(List<Product> products, List<String> input){
        //Returns true if the input list is equal to the number of products - 1
        // (first input is for client id)
        if(products.size()!= input.size()-1){
            System.out.println("There are "+products.size()+" products while "
                    +(input.size()-1)+" inputs have been given");
            return false;
        }
        return true;
    }
    private boolean checkStringForParsing(String input){
        //Returns true if input string can be successfully parsed to Integer and is higher or equal to 0
        try{
            int parsedInput = Integer.parseInt(input);
            if(parsedInput<0){
                System.out.println("Cannot have order volume or client id lower than 0");
                return false;
            }
            return true;
        }
        catch (NumberFormatException e){
            System.out.println("Please make sure to enter a valid input");
            return false;
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean checkInputListForParsing(List<String> input){
        //Calls checkStringForParsing() for every element in input
        for(int i=0;i< input.size();i++){
            if(!checkStringForParsing(input.get(i))){
                return false;
            }
        }
        return true;
    }
}
