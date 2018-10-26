package com.gaggle.givr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class LocationItemPage extends AppCompatActivity {
    Location location;

    TextView name;
    TextView latitude;
    TextView longitude;
    TextView streetAddress;
    TextView city;
    TextView state;
    TextView zip;
    TextView type;
    TextView phone;
    TextView website;

    Button Item_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_item_page);

        location = (Location) getIntent().getSerializableExtra("location");

        bindFields();
        setFields(location);
    }


    private void bindFields() {
        name = findViewById(R.id.name);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        streetAddress = findViewById(R.id.streetAddress);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        zip = findViewById(R.id.zip);
        type = findViewById(R.id.type);
        phone = findViewById(R.id.phone);
        website = findViewById(R.id.website);

        Item_button = findViewById(R.id.Item_button);
    }


    private void setFields(Location location) {
        name.setText(location.getName());
        latitude.setText(location.getLatitude());
        longitude.setText(location.getLongitude());
        streetAddress.setText(location.getStreetAddress());
        city.setText(location.getCity());
        state.setText(location.getState());
        zip.setText(location.getZip());
        type.setText(location.getType());
        phone.setText(location.getPhone());
        website.setText(location.getWebsite());
    }
    /*
     * Go to the item page
     */
    private void navigateToItemPage() {
        Intent ItemPage = new Intent(LocationItemPage.this, ItemPage.class);
        LocationItemPage.this.startActivity(ItemPage);
    }
}

