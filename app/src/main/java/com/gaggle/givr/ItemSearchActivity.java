package com.gaggle.givr;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

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

    public static ArrayList<Item> searchItemList(String pattern, ArrayList<Item> items) {
        ArrayList<Item> constructionList = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(pattern.toLowerCase())) {
                constructionList.add(item);
            }
        }
        return constructionList;
    }

    public static ArrayList<Item> searchLocationList(String pattern, ArrayList<Location> locations) {
        ArrayList<Item> constructionList = new ArrayList<>();
        for (Location loc : locations) {
            constructionList.addAll(searchItemList(pattern, loc.items));
        }
        return constructionList;
    }

}
