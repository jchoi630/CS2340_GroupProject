package com.gaggle.givr;
import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;



public class LocationActivity {

    public static void main (String[] args) {
        String csvFile = "\"C:\\..\\..\\..\\..\\Gaggle\\app\\src\\main\\java\\com\\gaggle\\givr\\LocationData.csv\"";

        BufferedReader br = null;

        String line = "";

        String csvSplitBy = ",";

        ArrayList<Location> locations = new ArrayList<>();


        try {

            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {


                String[] locationData = line.split(csvSplitBy);

                if (!(locationData[0].equals("Key"))) {

                    Location l = new Location(locationData[0], locationData[1], locationData[2], locationData[3], locationData[4], locationData[5], locationData[6], locationData[7], locationData[8], locationData[9], locationData[10]);

                    locations.add(l);

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
    }
}