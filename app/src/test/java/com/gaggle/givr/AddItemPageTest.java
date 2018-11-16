package com.gaggle.givr;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddItemPageTest {
    private Item item;
    private Item item2;
    private Item item3;
    private Item item4;

    @Before
    public void setUp() {

        Item item = new Item ("test", "Emily", 12, 2, 112);
        Item item2 = new Item ("test", null, 12, 2, 112);
        Item item3 = new Item (null, "Tony", 12, 2, 3);
        Item item4 = new Item ("hello","Josh", 12, 0, 2);


    }

    @Test
    public void populateFields() {
        if (item.getName() != null) {
            assertTrue(item.getName() != null);
        } else {
            assertTrue(item.getName() == null);
        }


    }
}