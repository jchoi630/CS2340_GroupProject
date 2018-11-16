package com.gaggle.givr;

import java.util.HashMap;
import java.util.Map;

/*
* the user class where we define what a user needs and has.
*/
public class User {
    String email;
    String password;
    String type;

    public static Map<String, User> userMap = new HashMap<>();

    /*
    * @param email the email
    * @param password the password
    * @param type the type of user (admin, normal)
    */
    public User(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
