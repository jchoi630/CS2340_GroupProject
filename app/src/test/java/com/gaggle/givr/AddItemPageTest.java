package com.gaggle.givr;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddItemPageTest {
    private Item item;
    private Item item2;
    private Item item3;
    private Item item4;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {

        Item item = new Item ("test", "Emily", 12, 2, 112);
        Item item2 = new Item ("test", null, 12, 2, 112);
        Item item3 = new Item (null, "Tony", 12, 2, 3);
        Item item4 = new Item ("hello","Josh", 12, 0, 2);


    }

    @Test (timeout = TIMEOUT)
    public void populateFields() {
        if (item.getLocation() != null && item.getName() != null) {
            assertTrue(item.getName() != null);
        } else {
            assertTrue(item.getName() == null);
        }

        if (item2.getName() != null && item2.getLocation() != null) {
            assertTrue(item2.getName() != null);
        } else {
            assertTrue(item2.getName() == null);
        }

        if (item3.getName() != null || item3.getLocation() != null) {
            assertTrue(item3.getName() != null);
        } else {
            assertTrue(item3.getName() == null);
        }

        if (item4.getLocation() == "hello" && item4.getName() != null) {
            assertTrue(item4.getName() != null);
        } else {
            assertTrue(item4.getName() == null);
        }

    }
}