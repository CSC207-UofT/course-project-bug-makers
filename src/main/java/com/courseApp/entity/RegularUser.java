package com.courseApp.entity;
import java.util.List;
import java.util.ArrayList;

/**
 * RegularUser is a subclass of User. This user entity has a password.
 * RegUser object will be saved in a database.
 * More commands than AnonymousUser.
 */
public class RegularUser extends User{


    private String password;
    public List<String> searchHistory;
    public RegularUser(String username, String password){
        super(username);
        this.password = password;

    }

    public String getPassword() {
        return password;
    }
}
