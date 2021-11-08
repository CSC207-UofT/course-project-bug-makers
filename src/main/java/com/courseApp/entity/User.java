

package com.courseApp.entity;

import com.courseApp.constants.Constants;
import com.courseApp.utils.PasswordEncoderMD5;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;

/**
 * Abstract class representing the users of this app. User has a username for identification purpose.
 */
public class User {

    private  String username;
    private  ArrayList<String> courseList; // High priority ControlPresentInfo course planning
    private  ArrayList<String> wishList; // Low priority ControlPresentInfo course planning
    private  ArrayList<Schedule> scheduleList; // List for storing schedule objects
    private  String userRole; // User role
    private ArrayList<UserReview> reviewList;
    private  String encryptedPassword;

    /**
     * Constructor for Bson, instantiate a User entity.
     *
     * @param username Bson username
     * @param courseList Bson courseList
     * @param wishList Bson wishList
     * @param scheduleList Bson scheduleList
     * @param userRole Bson userRole
     * @param reviewList Bson reviewList
     * @param encryptedPassword Bson encryptedPassword
     */
    @BsonCreator
    public User(@BsonProperty(Constants.USERNAME) String username,
                @BsonProperty(Constants.COURSE_LIST) ArrayList<String> courseList,
                @BsonProperty(Constants.WISH_LIST) ArrayList<String> wishList,
                @BsonProperty(Constants.SCHEDULE_LIST) ArrayList<Schedule> scheduleList,
                @BsonProperty(Constants.USER_ROLE) String userRole,
                @BsonProperty(Constants.REVIEW_LIST) ArrayList<UserReview> reviewList,
                @BsonProperty(Constants.ENCRYPTED_PASSWORD) String encryptedPassword) {
        this.username = username;
        this.courseList = courseList;
        this.wishList = wishList;
        this.scheduleList = scheduleList;
        this.userRole = userRole;
        this.reviewList = reviewList;
        this.encryptedPassword = encryptedPassword;
    }

    /**
     * Constructor for register user, instantiate a User entity.
     *
     * It will generate empty lists for User attributes.
     *
     * @param username username
     * @param raw_password unencrypted password
     */
    public User(String username, String raw_password){
        this.username = username;
        this.courseList = new ArrayList<>();
        this.wishList = new ArrayList<>();
        this.scheduleList = new ArrayList<>();
        this.userRole = Constants.REGULAR_USER;
        this.reviewList = new ArrayList<>();
        this.encryptedPassword = encryptPassword(raw_password);
    }

    /**
     * Check the password with the encrypted password string in the database.
     *
     * @param password raw password
     * @return true iff the password is correct, otherwise, false.
     */
    public boolean checkPassword(String password){
        password = PasswordEncoderMD5.encode(password);
        return password.equals(this.encryptedPassword);
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

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    private String encryptPassword(String rawPassword){
        return PasswordEncoderMD5.encode(rawPassword);
    }
}

