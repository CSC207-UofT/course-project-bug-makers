package com.courseApp.userService;

import com.courseApp.entity.Schedule;
import com.courseApp.entity.UserReview;

import java.util.ArrayList;

/**
 * Interface for realizing user data query.
 */
public interface UseQueryUserData {

    /**
     * Return course list for user. Note that method should be called after  authentication.
     *
     * @return List of Course List.
     */
    ArrayList<String> queryUserCourseList();

    /**
     * Return user wish list. Note that method should only be called after  authentication.
     *
     * @return List of wish list.
     */
    ArrayList<String> queryUserWishList();

    /**
     * Return user schedule list. Note that method should only be called after  authentication.
     *
     * @return List of user schedule
     */
    ArrayList<Schedule> queryUserScheduleList();

    /**
     * Return user schedule list of the targeted user. Note that this method should only be called after authentication
     *
     * @return List of User Review
     */
    ArrayList<UserReview> queryUserReviewList();


}


