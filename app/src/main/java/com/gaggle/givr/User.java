package com.gaggle.givr;

import java.util.HashMap;
import java.util.Map;

public class User {
    String email;
    String password;
    String type;

    public static Map<String, User> userMap = new HashMap<>();

    public User(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
