package com.gaggle.givr;

import java.util.HashMap;

public class User {
    String email;
    String password;
    String type;

    public static HashMap<String, User> userMap = new HashMap<>();

    public User(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
