package com.courseApp.entity;

import java.util.List;
import java.util.ArrayList;

/**
 * Abstract class representing the users of this app. User has a username for identification purpose.
 * Testing
 */
public abstract class User {



    public String username;
    public List<String> listCourse;
    public List<String> listWish;
    public List<Calendar> listCalendar;
    public User(String username){
        this.username = username;
        this.listCourse = new ArrayList<String>();
        this.listCalendar = new ArrayList<Calendar>();
        this.listWish = new ArrayList<String>();
    }

    public List<String> getListCourse() {
        return listCourse;
    }

    public List<String> getListWish() {
        return listWish;
    }

    public List<Calendar> getListCalendar() {
        return listCalendar;
    }
}
