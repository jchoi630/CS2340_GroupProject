package com.gaggle.givr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemSearchActivityTest {

    @Test
    public void searchItemsInItemList() {
        ArrayList<Item> testList = new ArrayList<>();
        testList.add(new Item("test", "AABB", 0, 0, 0));
        testList.add(new Item("test", "BBCC", 0, 0, 0));
        testList.add(new Item("test", "CCDD", 0, 0, 0));
        testList.add(new Item("test", "DDEE", 0, 0, 0));

        ArrayList<Item> resultList = new ArrayList<>();
        resultList.add(testList.get(1));
        resultList.add(testList.get(2));

        assertEquals(resultList, ItemSearchActivity.searchItemList("CC", testList));
    }
}