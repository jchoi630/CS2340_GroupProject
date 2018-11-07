package com.gaggle.givr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LocationListPage extends AppCompatActivity {
    private ArrayList<Location> locations;
    TinyDB tinydb;

    ListView locationListView;
    LocationListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list_page);
        tinydb = new TinyDB(this);

        locations = tinydb.getListLocation("locations");
        if (locations.size() == 0) {
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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void navigateToLocationItem(int pos) {
        Location location = locations.get(pos);
        Intent toLocationItem = new Intent(LocationListPage.this, LocationItemPage.class);
        toLocationItem.putExtra("location", (Serializable) location);
        toLocationItem.putExtra("locationPos", pos);
        LocationListPage.this.startActivity(toLocationItem);
    }

    public void navigateBackToLandingPage(View v) {
        //action you want, to start new activity, params are the things you go from (this page to next page)
        Intent backToLandingPage = new Intent(LocationListPage.this, LandingPage.class);
        LocationListPage.this.startActivity(backToLandingPage);
    }

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
                if (!(locationData[0].equals("Key"))) {
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
