package com.courseApp.entity;
import java.util.List;
import java.util.ArrayList;

/**
 * RegularUser is a subclass of User. This user entity has a password.
 * RegUser object will be saved ControlPresentInfo a database.
 * More commands than AnonymousUser.
 */
public class RegularUser extends User{

    public RegularUser(String username, ArrayList<String> courseList, ArrayList<String> wishList, ArrayList<Schedule> scheduleList, String userRole) {
        super(username, courseList, wishList, scheduleList, userRole);
    }
}
