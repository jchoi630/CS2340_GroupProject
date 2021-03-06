package com.gaggle.givr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

/**
* the list of all locations
*/
public class LocationListPage extends AppCompatActivity {
    private ArrayList<Location> locations;
    TinyDB tinydb;
    Button map_button;

    ListView locationListView;
    LocationListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list_page);
        tinydb = new TinyDB(this);

        locations = tinydb.getListLocation("locations");
        if (locations.isEmpty()) {
            Location.locationList = importCSV();
            locations = Location.locationList;
            tinydb.putListLocation("locations", Location.locationList);
        } else {
            Location.locationList = locations;
        }

        locationListView = findViewById(R.id.locationListView);
        listAdapter = new LocationListAdapter(locations, this);
        locationListView.setAdapter(listAdapter);
        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                navigateToLocationItem(pos);
            }
        });

//        map_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                navigateToMapPage(v);
//            }
//        });
    }

    private void bindFields() {
        map_button = findViewById(R.id.map_button);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
    * @param pos the number in the array of the location we are checking
    * an intent to go from current page to the more specifics of a certain location
    */
    public void navigateToLocationItem(int pos) {
        Location location = locations.get(pos);
        Intent toLocationItem = new Intent(LocationListPage.this, LocationItemPage.class);
        toLocationItem.putExtra("location", (Serializable) location);
        toLocationItem.putExtra("locationPos", pos);
        LocationListPage.this.startActivity(toLocationItem);
    }
    /**
     * @param v the page we wil be looking at next
     * an intent to go from current page back to the original landing page
     */
    public void navigateBackToLandingPage(View v) {
        //action you want, to start new activity, params are the things you go from (this page to next page)
        Intent backToLandingPage = new Intent(LocationListPage.this, LandingPage.class);
        LocationListPage.this.startActivity(backToLandingPage);
    }
    /**
     * @param v the page we wil be looking at next
     * an intent to go from current page to the map page with all the places!
     */
    public void navigateToMapPage(View v) {
        //action you want, to start new activity, params are the things you go from (this page to next page)
        Intent toMapPage = new Intent(LocationListPage.this, MapsActivity.class);
        toMapPage.putExtra("locations", locations);
        LocationListPage.this.startActivity(toMapPage);
    }
    /**
     * @param v the page we wil be looking at next
     * an intent to go from current page to the search page
     */
    public void navigateToSearchPage(View v) {
        Intent searchPage = new Intent(LocationListPage.this, ItemSearchActivity.class);
        LocationListPage.this.startActivity(searchPage);
    }

    private ArrayList<Location> importCSV() {
        ArrayList<Location> locationList = new ArrayList<>();
        InputStream inputStream = getResources().openRawResource(R.raw.location_data);
        BufferedReader br = null;
        String csvSplitBy = ",";
        String line;

        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                String[] locationData = line.split(csvSplitBy);
                if (!"Key".equals(locationData[0])) {
                    Location l = new Location(locationData[0], locationData[1], locationData[2], locationData[3], locationData[4], locationData[5], locationData[6], locationData[7], locationData[8], locationData[9], locationData[10]);
                    locationList.add(l);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return locationList;
    }
}
