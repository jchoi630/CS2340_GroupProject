package com.gaggle.givr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemListPage extends AppCompatActivity {
    private ArrayList<Item> items = new ArrayList<>();

    ListView itemListView;
    ItemListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_page);

        itemListView = findViewById(R.id.locationListView);
        listAdapter = new ItemListAdapter(items, this);
        itemListView.setAdapter(listAdapter);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                navigateToItem(items.get(pos));
            }
        });

    }

    public void navigateToItem(Item item) {
        Intent toItem = new Intent(ItemListPage.this, AddItemPage.class);
        toItem.putExtra("item", (Serializable) item);
        ItemListPage.this.startActivity(toItem);
    }

    public void navigateBackToLocationItemPage(View v) {
        //action you want, to start new activity, params are the things you go from (this page to next page)
        Intent backToLocationItemPage = new Intent(ItemListPage.this, LocationItemPage.class);
        ItemListPage.this.startActivity(backToLocationItemPage);
    }

}