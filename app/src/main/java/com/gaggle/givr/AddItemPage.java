package com.gaggle.givr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class AddItemPage extends AppCompatActivity {

    TextView locationField;
    EditText nameField;
    EditText quantityField;
    EditText weightField;
    EditText idField;

    Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bindFields();

        location = (Location) getIntent().getExtras().getSerializable("location");

        final Item currItem = (Item) getIntent().getExtras().getSerializable("item");
        populateFields(currItem);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TBD", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                submit(currItem);

            }
        });

    }
    private void bindFields() {
        nameField = findViewById(R.id.name);
        quantityField = findViewById(R.id.quantity);
        weightField = findViewById(R.id.weight);
        idField = findViewById(R.id.id);

    }
    public void submit(Item item) {
        // TODO: Set to edit item isnstead of adding a new one.
//        if (item.getName() != null) {
//            item.setName(nameField.getText().toString());
//            item.setQuantity(Integer.parseInt(quantityField.getText().toString()));
//            item.setWeight(Integer.parseInt(weightField.getText().toString()));
//            item.setId(Integer.parseInt(idField.getText().toString()));
//        } else {
//        }
        location.items.add(new Item(
                "test",
                nameField.getText().toString(),
                Integer.parseInt(quantityField.getText().toString()),
                Integer.parseInt(weightField.getText().toString()),
                Integer.parseInt(idField.getText().toString())
        ));
        navigateBackToLocationItemPage();
        Log.d("submit","TEST I'm a big kid now!");

    }
    public void populateFields(Item item){
        System.out.println("test: " + item.getName());
        if (item.getName() != null) {
            nameField.setText(item.getName());
            quantityField.setText(String.valueOf(item.getQuantity()));
            weightField.setText(String.valueOf(item.getWeight()));
            idField.setText(String.valueOf(item.getId()));
        }
    }
    public void navigateBackToLocationItemPage() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("location", location);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Handle back button press for startActivityForResult
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
