package com.gaggle.givr;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;



import static com.gaggle.givr.R.layout.activity_item_page;

public class ItemPage extends AppCompatActivity {
    Item item;
    TextView name;
    TextView quantity;
    TextView weight;
    TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_item_page);
        item = (Item) getIntent().getSerializableExtra("item");

        bindFields();
        setFields(item);
    }
    private void bindFields() {
        name = findViewById(R.id.name);
        quantity = findViewById(R.id.quantity);
        weight = findViewById(R.id.weight);
        id = findViewById(R.id.id);

    }
    private void setFields(Item item) {
        name.setText(item.getName());
        quantity.setText(item.getQuantity());
        weight.setText(item.getWeight());
        id.setText(item.getId());


    }


}
