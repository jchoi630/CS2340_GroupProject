package com.gaggle.givr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class AddItemPage extends AppCompatActivity {

    TextView locationField;
    EditText nameField;
    EditText quantityField;
    EditText weightField;
    EditText idField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bindFields();
        populateFields((Item) getIntent().getSerializableExtra("item"));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TBD", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    private void bindFields() {
        nameField = findViewById(R.id.name);
        quantityField = findViewById(R.id.quantity);
        weightField = findViewById(R.id.weight);
        idField = findViewById(R.id.id);

    }
    public void submit(View v) {
        Item.itemList.add(new Item(
                "test",
                nameField.getText().toString(),
                Integer.parseInt(quantityField.getText().toString()),
                Integer.parseInt(weightField.getText().toString()),
                Integer.parseInt(idField.getText().toString())
        ));
        System.out.println("TEST I'm a big kid now!");
    }
    public void populateFields(Item item){
        if (item != null) {
            nameField.setText(item.getName());
            quantityField.setText(String.valueOf(item.getQuantity()));
            weightField.setText(String.valueOf(item.getWeight()));
            idField.setText(String.valueOf(item.getId()));
        }
    }
}
