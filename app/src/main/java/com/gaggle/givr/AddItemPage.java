package com.gaggle.givr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
/**
* the class where we are adding and editing items
*/
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
    }
    private void bindFields() {
        nameField = findViewById(R.id.name);
        quantityField = findViewById(R.id.quantity);
        weightField = findViewById(R.id.weight);
        idField = findViewById(R.id.id);

    }

    /**
     * @param v view passed in by onClick
     * adds an item into the Item object
     */
    public void submit(View v) {
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
        System.out.println("TEST I'm a big kid now!");
    }
    /**
    * @param item the item we will populate the edit field with
    * use this to do edits to the item
    */
    public void populateFields(Item item){
        System.out.println("test: " + item.getName());
        if (item.getName() != null) {
            nameField.setText(item.getName());
            quantityField.setText(String.valueOf(item.getQuantity()));
            weightField.setText(String.valueOf(item.getWeight()));
            idField.setText(String.valueOf(item.getId()));
        }
    }
    /**
    * an intent to go from current page to go back to the location specific page.
    */
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
