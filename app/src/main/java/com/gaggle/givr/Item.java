package com.gaggle.givr;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    public static ArrayList<Item> itemList = new ArrayList<>();
    private String location;
    private String name;
    private int quantity;
    private int weight;
    private int id;

    public Item (String location, String name, int quantity, int weight, int id){
        this.location = location;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.id = id;
    }
    public Item(){

    }
    public String getLocation() {
        return location;
    }
    public String getName(){
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getWeight() {
        return weight;
    }
    public int getId() {
        return id;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setId(int id) {
        this.id = id;
    }
}
