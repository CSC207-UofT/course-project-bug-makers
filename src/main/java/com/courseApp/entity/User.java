

package com.courseApp.entity;

import java.util.ArrayList;

/**
 * Abstract class representing the users of this app. User has a username for identification purpose.
 */
public abstract class User {

    protected String username;
    protected ArrayList<String> courseList; // High priority ControlPresentInfo course planning
    protected ArrayList<String> wishList; // Low priority ControlPresentInfo course planning
    protected ArrayList<Schedule> scheduleList; // List for storing schedule objects
    protected String userRole; // User role

    public User(String username,
                ArrayList<String> courseList,
                ArrayList<String> wishList,
                ArrayList<Schedule> scheduleList,
                String userRole) {
        this.username = username;
        this.courseList = courseList;
        this.wishList = wishList;
        this.scheduleList = scheduleList;
        this.userRole = userRole;
    }
}

