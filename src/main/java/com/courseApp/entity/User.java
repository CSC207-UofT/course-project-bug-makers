

package com.courseApp.entity;

import java.util.ArrayList;

/**
 * Abstract class representing the users of this app. User has a username for identification purpose.
 */
public abstract class User {

    protected String username;
    protected ArrayList<String> courseList;
    protected ArrayList<String> wishList;
    protected ArrayList<Schedule> scheduleList;
    protected String userRole;

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

