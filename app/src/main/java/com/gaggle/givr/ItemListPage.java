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

    ListView itemListView;
    ItemListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_page);

        itemListView = findViewById(R.id.item_list_view);
        listAdapter = new ItemListAdapter(Item.itemList, getApplicationContext());
        itemListView.setAdapter(listAdapter);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                navigateToEditItem(Item.itemList.get(pos));
            }
        });
        Button button= (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ItemListPage.this,LocationListPage.class));
                System.out.println("CLICKED");
            }
        });

    }



    public void navigateToEditItem(Item item) {
        Intent toItem = new Intent(ItemListPage.this, AddItemPage.class);
        toItem.putExtra("item", (Serializable) item);
        ItemListPage.this.startActivity(toItem);
    }
    public void navigateToAddItem(View v) {
        Intent toItem = new Intent(ItemListPage.this, AddItemPage.class);
        toItem.putExtra("item", (Serializable) new Item());
        ItemListPage.this.startActivity(toItem);
    }

    public void navigateBackToLocationItemPage(View v) {
        //action you want, to start new activity, params are the things you go from (this page to next page)
        Intent backToLocationItemPage = new Intent(ItemListPage.this, LandingPage.class);
        ItemListPage.this.startActivity(backToLocationItemPage);
    }

}