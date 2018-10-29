package com.gaggle.givr;

import java.io.Serializable;

public class Item implements Serializable {
    private String key;
    private String name;
    private int quantity;
    private int weight;
    private int id;

    public Item (String key, String name, int quantity, int weight, int id){
        this.key = key;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.id = id;
    }
    public String getKey() {
        return key;
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

    public void setKey(String key) {
        this.key = key;
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
