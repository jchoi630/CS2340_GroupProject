package com.gaggle.givr;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * how to specify the locations
 */
public class Location implements Serializable {
    public static ArrayList<Location> locationList;

    public ArrayList<Item> items;

    private String key;
    private String name;
    private String latitude;
    private String longitude;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;

    /**
     * the constructor for a location
     * @param key the key
     * @param name the name
     * @param latitude the lat
     * @param longitude the lng
     * @param streetAddress the street address
     * @param city the city
     * @param state the state
     * @param zip the zipcode
     * @param type the type of location
     * @param phone the number
     * @param website the website
     */
    public Location(String key, String name, String latitude, String longitude, String streetAddress, String city, String state, String zip, String type, String phone, String website) {
        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phone = phone;
        this.website = website;

        this.items = new ArrayList<>();
    }

    /**
     * getter for  key
     * @return key
     */
    public String getKey() {
        return key;
    }
    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * getter for latitude
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }
    /**
     * getter for longtitude
     * @return longtitude
     */
    public String getLongitude() {
        return longitude;
    }
    /**
     * getter for address
     * @return address
     */
    public String getStreetAddress() {
        return streetAddress;
    }
    /**
     * getter for city
     * @return city
     */
    public String getCity() {
        return city;
    }
    /**
     * getter for state
     * @return state
     */
    public String getState() {
        return state;
    }
    /**
     * getter for zipcode
     * @return zipcode
     */
    public String getZip() {
        return zip;
    }
    /**
     * getter for type
     * @return type of location
     */
    public String getType() {
        return type;
    }
    /**
     * getter for number
     * @return number
     */
    public String getPhone() {
        return phone;
    }
    /**
     * getter for website
     * @return website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * setter for key
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     *  the setter for name
     * @param name the name of location
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * the settter for lat
     * @param latitude of location
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * the setter for longtitude
     * @param longitude for location
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * setter for street address
     * @param streetAddress for locatoin
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * setter for city
     * @param city for location
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * setter for state
     * @param state for location
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * setter for zipcode
     * @param zip for location
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * setter for type of location
     * @param type for location
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * setter for phone number
     * @param phone for specific location
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * setter for website
     * @param website for location
     */
    public void setWebsite(String website) {
        this.website = website;
    }
}
