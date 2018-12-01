package com.gaggle.givr;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * where we search for items
 */
public class ItemSearchActivity extends Activity {
    Location location;
    boolean isLocationSpecific;

    EditText searchField;

    ListView itemListView;
    ItemListAdapter listAdapter;
    ArrayList<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);

        try {
            location = (Location) getIntent().getExtras().getSerializable("location");
            isLocationSpecific = true;
        } catch (NullPointerException e) {
            location = null;
        }

        searchField = findViewById(R.id.searchField);

        itemListView = findViewById(R.id.item_list_view);
        listAdapter = new ItemListAdapter(itemList, getApplicationContext());
        itemListView.setAdapter(listAdapter);
    }

    /**
     * how we begin the search process
     * @param v the page we are viewing
     */
    public void initiateSearch(View v) {
        String pattern = searchField.getText().toString();
        if (isLocationSpecific) {
            itemList = searchItemList(pattern, location.items);
        } else {
            itemList = searchLocationList(pattern, Location.locationList);
        }
        listAdapter.clear();
        listAdapter.addAll(itemList);
    }

    /**
     * an arraylist of items
     * @param pattern the chars we enter
     * @param items the arraylist fo items we are checking within
     * @return the list of items that match
     */
    public ArrayList<Item> searchItemList(String pattern, ArrayList<Item> items) {
        ArrayList<Item> constructionList = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(pattern.toLowerCase())) {
                constructionList.add(item);
            }
        }
        return constructionList;
    }

    /**
     * how we check to see what is in which location
     * @param pattern the chars we enter
     * @param locations where this is coming from
     * @return the list of items in that locations that match
     */
    public ArrayList<Item> searchLocationList(String pattern, ArrayList<Location> locations) {
        ArrayList<Item> constructionList = new ArrayList<>();
        for (Location loc : locations) {
            constructionList.addAll(searchItemList(pattern, loc.items));
        }
        return constructionList;
    }

}
