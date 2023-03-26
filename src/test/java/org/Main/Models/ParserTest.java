package org.Main.Models;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void parseClientIdTest(){
        Parser parser = new Parser();
        List<String> input = new ArrayList<>();
        input.add("3");
        input.add("0");
        input.add("10000");
        input.add("15000");
        input.add("0");

        //Valid Input
        //Should remove the first element of the list when called
        assertEquals(3,parser.parseClientId(input));
        assertEquals("0",input.get(0));

        //Invalid input
        input.set(0,"wasd");
        assertEquals(0,parser.parseClientId(input));
    }

    @Test
    public void parseInputTest(){
        Parser parser = new Parser();
        List<String> input = new ArrayList<>();
        input.add("3");
        input.add("0");
        input.add("10000");
        input.add("15000");
        input.add("0");
        int[] parsedInput = parser.parseInput(input);
        int[] expected = new int[]{3,0,10000,15000,0};

        //Valid input
        for(int i=0;i<expected.length;i++){
            assertEquals(expected[i],parsedInput[i]);
        }


        input.set(2,"wasd");
        assertNull(parser.parseInput(input));
    }

}