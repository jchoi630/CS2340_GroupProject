package com.gaggle.givr;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
public class AddItemPageTest {
    @Before
    public  void setup() {
        Item item = new Item("temp","mary",23,11,1232);
        item.getName();
        item.getQuantity();

        Item item2 = new Item("temp", null , 12, 11, 1123 );
        item2.getName();
        item2.getQuantity();

    }

    @Test
    public void populateFields(Item item){
       assertTrue(  " This is true!", item.getName() == null);
       assertTrue("This is not true! ", item.getName() == null);

    }
}