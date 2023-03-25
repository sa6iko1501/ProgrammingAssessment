package org.Main.Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    @Test
    public void constructorTest(){
        Client client = new Client(1,5,7,3,null);
        assertEquals(1,client.getId());
        assertEquals(5,client.getBasicClientDiscount());
        assertEquals(7,client.getVolumeDiscount10000());
        assertEquals(3,client.getVolumeDiscount30000());
    }

    @Test
    public void constructorExceptions(){
        //Id = 0
        assertThrows(IllegalArgumentException.class,
                ()->new Client(0,5,7,3,null)
        );
        //Id Edge Negative
        assertThrows(IllegalArgumentException.class,
                ()->new Client(Integer.MIN_VALUE,5,7,3,null)
        );
        //Basic client Discount lower than 0
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,-1,7,3,null)
        );
        //Basic client Discount higher than 99.9
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,100,7,3,null)
        );
        //Basic client Discount Edge Positive
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,Double.MAX_VALUE,7,3,null)
        );
        //Volume 10 000 discount lower than 0
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,5,-1,3,null)
        );
        //Volume 10 000 discount higher than 99.9
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,5,100,3,null)
        );
        //Volume 10 000 discount positive and negative edge cases
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,5,Double.MAX_VALUE,3,null)
        );
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,5,-2.225E-307,3,null)
        );
        //Volume 30 000 discount lower than 0
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,5,5,-0.01,null)
        );
        //Volume 30 000 discount higher than 99.9
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,5,5,100,null)
        );
        //Volume 30 000 positive and negative edge cases
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,5,5,-2.225E-307,null)
        );
        assertThrows(IllegalArgumentException.class,
                ()->new Client(1,5,5,Double.MAX_VALUE,null)
        );
    }

    @Test
    public void setBasicClientDiscountTest(){
        Client client = new Client(1,5.5,3,0,null);
        client.setBasicClientDiscount(3.3);
        assertEquals(3.3,client.getBasicClientDiscount());
        //Basic Client discount higher than 99.9
        assertThrows(IllegalArgumentException.class,
                ()-> client.setBasicClientDiscount(100)
                );
        //Basic Client discount lower than 0
        assertThrows(IllegalArgumentException.class,
                ()-> client.setBasicClientDiscount(-0.01)
        );
        //Basic Client discount edge cases
        assertThrows(IllegalArgumentException.class,
                ()-> client.setBasicClientDiscount(Double.MAX_VALUE)
        );
        assertThrows(IllegalArgumentException.class,
                ()-> client.setBasicClientDiscount(-2.225E-307)
        );
    }

    @Test
    public void setVolumeDiscount10000(){
        Client client = new Client(1,5.5,3,0,null);
        client.setVolumeDiscount10000(40);
        assertEquals(40,client.getVolumeDiscount10000());
        //clientVolumeDiscount10000 higher than 99.9
        assertThrows(IllegalArgumentException.class,
                ()->client.setVolumeDiscount10000(100)
                );
        //clientVolumeDiscount10000 lower than 0
        assertThrows(IllegalArgumentException.class,
                ()->client.setVolumeDiscount10000(100)
        );
        //Positive and negative edge cases
        assertThrows(IllegalArgumentException.class,
                ()->client.setVolumeDiscount10000(Double.MAX_VALUE)
        );
        assertThrows(IllegalArgumentException.class,
                ()->client.setVolumeDiscount10000(-2.225E-307)
        );
    }
    @Test
    public void setVolume30000Discount(){
        Client client = new Client(1,5.5,3,0,null);
        client.setVolumeDiscount30000(40);
        assertEquals(40,client.getVolumeDiscount30000());
        //clientVolumeDiscount30000 higher than 99.9
        assertThrows(IllegalArgumentException.class,
                ()->client.setVolumeDiscount30000(100)
        );
        //clientVolumeDiscount30000 lower than 0
        assertThrows(IllegalArgumentException.class,
                ()->client.setVolumeDiscount30000(100)
        );
        //Positive and negative edge cases
        assertThrows(IllegalArgumentException.class,
                ()->client.setVolumeDiscount30000(Double.MAX_VALUE)
        );
        assertThrows(IllegalArgumentException.class,
                ()->client.setVolumeDiscount30000(-2.225E-307)
        );
    }

}