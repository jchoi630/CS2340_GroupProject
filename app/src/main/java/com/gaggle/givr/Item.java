package com.gaggle.givr;

import java.io.Serializable;
import java.util.ArrayList;
/**
* Shows what an item is made of
* Team Gaggle
*
*/

public class Item implements Serializable {
    public static ArrayList<Item> itemList = new ArrayList<>();

    private String location;
    private String name;
    private int quantity;
    private int weight;
    private int id;
/**
* @param location location of item
* @param name name of item
* @param quantity quantity of item
* @param weight weight of tiem
* @param id if of item
* constructor for Items
*
* */
    public Item (String location, String name, int quantity, int weight, int id){
        this.location = location;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.id = id;
    }
    /**
    * no param constructor for Item
    */
    public Item(){

    }
    /**
    * getter for location
     * @return the lcoation
    */
    public String getLocation() {
        return location;
    }
    /**
     * getter for name
     * @return  the name
     */
    public String getName(){
        return name;
    }
    /**
     * getter for quantity
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * getter for weight
     * @return the weight of item
     */
    public int getWeight() {
        return weight;
    }
    /**
     * getter for id
     * @return the id of the item
     */
    public int getId() {
        return id;
    }
    /**
     * @param  location the location
     * setter for location
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * @param name  the name of item
     * setter for name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param quantity  the quantity
     * setter for quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * @param weight  the weight of item
     * setter for weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
    /**
     * @param id  the item's id
     * setter for id
     */
    public void setId(int id) {
        this.id = id;
    }
}
