package com.gaggle.givr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemListPage extends AppCompatActivity {
    TinyDB tinydb;

    ListView itemListView;
    ItemListAdapter listAdapter;
    Location location;
    int locationPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_page);
        tinydb = new TinyDB(this);

        location = (Location) getIntent().getExtras().getSerializable("location");
        locationPos = getIntent().getExtras().getInt("locationPos");

        itemListView = findViewById(R.id.item_list_view);
        listAdapter = new ItemListAdapter(location.items, getApplicationContext());
        itemListView.setAdapter(listAdapter);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                navigateToEditItem(location.items.get(pos));
            }
        });
        Button button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigateToLocationListPage();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        location = (Location) data.getSerializableExtra("location");
        Location.locationList.set(locationPos, location);
        tinydb.putListLocation("locations", Location.locationList);
        listAdapter.clear();
        listAdapter.addAll(location.items);
    }

    public void navigateToEditItem(Item item) {
        Intent toItem = new Intent(ItemListPage.this, AddItemPage.class);
        toItem.putExtra("item", (Serializable) item);
        toItem.putExtra("location", (Serializable) location);
        ItemListPage.this.startActivityForResult(toItem, 1);
    }
    public void navigateToAddItem(View v) {
        Intent toItem = new Intent(ItemListPage.this, AddItemPage.class);
        toItem.putExtra("item", (Serializable) new Item());
        toItem.putExtra("location", (Serializable) location);
        ItemListPage.this.startActivityForResult(toItem, 1);
    }

    public void navigateBackToLocationItemPage(View v) {
        //action you want, to start new activity, params are the things you go from (this page to next page)
        Intent backToLocationItemPage = new Intent(ItemListPage.this, LandingPage.class);
        ItemListPage.this.startActivity(backToLocationItemPage);
    }

    public void navigateToLocationListPage() {
        startActivity(new Intent(ItemListPage.this,LocationListPage.class));
    }

}