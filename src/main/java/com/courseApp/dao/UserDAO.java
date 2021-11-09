package com.courseApp.dao;

import com.courseApp.entity.Schedule;
import com.courseApp.entity.User;
import com.courseApp.entity.UserReview;

import java.util.ArrayList;

/**
 * UserDAO interface for querying user data, register action and login action.
 */
public interface UserDAO {

    /**
     * Return a User obj, syncing with the database iff the password is correct.
     *
     * @return User obj iff password is correct
     */
    User queryUser();

    /**
     * Check if the username already exists ControlPresentInfo the DB.
     *
     * @return true iff the username is ControlPresentInfo the DB, otherwise, false
     */
    Boolean queryUserInDB();

    /**
     * User register service with user existence checked.
     *
     * @return true iff register is successful, otherwise, false
     */
    Boolean userRegister();

    /**
     * User Login service with password check and username check.
     *
     * @return ture iff login is successful, otherwise, false.
     */
    Boolean userLogin();

    /**
     * Query User's role
     *
     * @return user's role
     */
    String queryUserRole();

    /**
     * Query user's course list
     *
     * @return user's course list
     */
    ArrayList<String> queryCourseList();

    /**
     * Query user's wish list
     *
     * @return user's wish list
     */
    ArrayList<String> queryWishList();

    /**
     * Query user's schedule list
     *
     * @return user's schedule list with no schedule map ControlPresentInfo the schedule object.
     */
    ArrayList<Schedule> queryScheduleList();

    /**
     * Rewrite the course list in the database.
     *
     * @param courseList User course list
     * @return ture iff the update is successful
     */
    boolean updateCourseList(ArrayList<String> courseList);

    /**
     * Rewrite the wish list in the database.
     *
     * @param wishList User wish list
     * @return ture iff the update is successful
     */
    boolean updateWishList(ArrayList<String> wishList);

    /**
     * Rewrite schedule list in the database.
     *
     * @param scheduleList list of schedule
     * @return ture iff the update is successful
     */
    boolean updateScheduleList(ArrayList<Schedule> scheduleList);

    /**
     * Query the user review list, corresponding to the targeted user.
     *
     * @return ArrayList of User Review entity.
     */
    ArrayList<UserReview> queryUserReviewList();

    /**
     * Rewrite the review list in the database
     *
     * @param reviewList list of UserReviews
     * @return true iff teh update is successful
     */
    boolean updateUserReviewList(ArrayList<UserReview> reviewList);

}
