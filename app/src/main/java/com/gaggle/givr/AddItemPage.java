package com.gaggle.givr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddItemPage extends AppCompatActivity {
    /* ************************
        Widgets we will need for binding and getting information
     */
    Item item;
    private TextView keyField;
    private EditText nameField;
    private EditText quantityField;
    private EditText weightField;
    private EditText idField;


    /* ***********************
       flag for whether this is a new student being added or an existing item being edited;
    */
    private boolean needsEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TBD", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    nameField = (EditText) findViewById(R.id.name);
    quantityField = (EditText)findViewById(R.id.quantity);
    weightField = (EditText)findViewById(R.id.weight);
    idField = (EditText) findViewById(R.id.id);

}
