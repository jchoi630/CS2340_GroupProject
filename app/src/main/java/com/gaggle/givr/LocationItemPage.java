package com.gaggle.givr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.io.Serializable;
/**
* The specifics of a location.
*/

public class LocationItemPage extends AppCompatActivity {
    Location location;
    int locationPos;

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

    Button item_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_item_page);

        location = (Location) getIntent().getExtras().getSerializable("location");
        locationPos = getIntent().getExtras().getInt("locationPos");

        bindFields();
        setFields(location);

        item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToItemPage();
            }
        });
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

        item_button = findViewById(R.id.item_button);
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
/**
* an intent to go from this page to the item page
*/
    public void navigateToItemPage() {
        Intent itemListPage = new Intent(LocationItemPage.this, ItemListPage.class);
        itemListPage.putExtra("location", (Serializable) location);
        itemListPage.putExtra("locationPos", locationPos);
        LocationItemPage.this.startActivity(itemListPage);
    }
    /**
     * @param v the view that shows us.
     * an intent to go from this page to the item search page
     */
    public void navigateToSearch(View v) {
        Intent searchPage = new Intent(LocationItemPage.this, ItemSearchActivity.class);
        searchPage.putExtra("location", location);
        LocationItemPage.this.startActivity(searchPage);
    }
}

