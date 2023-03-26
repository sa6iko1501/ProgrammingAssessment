package org.Main.Models;

import java.util.List;

public class Parser {
    public int[] parseInput(List<String> input){
        try{
            int[] productVolumes = new int[input.size()];
            for(int i=0;i< productVolumes.length;i++){
                productVolumes[i] = Integer.parseInt(input.get(i));
            }

            return productVolumes;
        }
        catch(NumberFormatException | NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public int parseClientId(List<String> input){
        try{
            int clientId = Integer.parseInt(input.get(0));
            input.remove(input.get(0));
            return clientId;
        }
        catch (NumberFormatException | NullPointerException e){
            e.printStackTrace();
            return 0;
        }
    }
}
