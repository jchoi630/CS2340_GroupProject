package com.gaggle.givr;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChaselynItemSearchActivityTest {

    @Test
    public void searchLocationList() {
        ArrayList<Location> testLocation = new ArrayList<>();
        testLocation.add(new Location("test",
                "test1", "test2","test3",
                "test111","test11111",
                "test111111", "test11111111", "test111111",
                "tesst", "testttttt"));
        testLocation.add(new Location("test123123132",
                "test131321", "test21231","test37889",
                "test1154398534834","test111153487431",
                "test111358934589354111", "test11115798348951111", "test11115348954811",
                "tes98543985st", "testtt95495478953489ttt"));
        testLocation.add(new Location("test798t798t897et",
                "test95389089041", "test5497835832","tes9837897893894t3",
                "test1tyhiughiuk11","test1ihjkfds1111",
                "test1111fgjkss11", "test11t67uygjhy89iuhk111111",
                "test1111ghukjfdshuigf11",
                "teghiugdhgdfhuifgdsst", "testtthiughkgfttt"));

        ArrayList<Location> testResult = new ArrayList<>();
        testResult.add(testLocation.get(1));
        testResult.add(testLocation.get(2));
        testResult.add(testLocation.get(3));

        assertEquals(testResult,ItemSearchActivity.searchLocationList("me",testLocation));
    }
}