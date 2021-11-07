

package com.courseApp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing the users of this app. User has a username for identification purpose.
 */
public class User {

    private  String username;
    private  ArrayList<String> courseList; // High priority ControlPresentInfo course planning
    private  ArrayList<String> wishList; // Low priority ControlPresentInfo course planning
    private  ArrayList<Schedule> scheduleList; // List for storing schedule objects
    private final String userRole; // User role
    private ArrayList<UserReview> reviewList;

    @Deprecated
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

    public User(String username,
                ArrayList<String> courseList,
                ArrayList<String> wishList,
                ArrayList<Schedule> scheduleList,
                String userRole,
                ArrayList<UserReview> reviewList) {
        this.username = username;
        this.courseList = courseList;
        this.wishList = wishList;
        this.scheduleList = scheduleList;
        this.userRole = userRole;
        this.reviewList = reviewList;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getCourseList() {
        return courseList;
    }

    public ArrayList<String> getWishList() {
        return wishList;
    }

    public ArrayList<Schedule> getScheduleList() {
        return scheduleList;
    }

    public String getUserRole() {
        return userRole;
    }

    public ArrayList<UserReview> getReviewList() {
        return reviewList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCourseList(ArrayList<String> courseList) {
        this.courseList = courseList;
    }

    public void setWishList(ArrayList<String> wishList) {
        this.wishList = wishList;
    }

    public void setScheduleList(ArrayList<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public void setReviewList(ArrayList<UserReview> reviewList) {
        this.reviewList = reviewList;
    }
}

