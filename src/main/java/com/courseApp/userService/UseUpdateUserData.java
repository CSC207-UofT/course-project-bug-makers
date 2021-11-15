package com.courseApp.userService;

import com.courseApp.entity.Schedule;
import com.courseApp.entity.UserReview;

/**
 * Interface for realizing user data update.
 */
@SuppressWarnings("ALL")
public interface UseUpdateUserData {

    /**
     * Insert one course to the user course list.
     * Note that method should only be called after  authentication.
     *
     * @param courseCode course code
     * @return ture iff the insertion is successful
     */
    boolean insertOneCourse( String courseCode);

    /**
     * Insert one course to the wishList.
     * Note that method should be called after  authentication.
     *
     * @param courseCode course code
     * @return true iff the insertion is successful
     */
    boolean insertOneWish(String courseCode);

    /**
     * Insert one user review to the reviewList.
     * Note that method should be called after  authentication.
     *
     * @param userReview user review
     * @return true iff the insertion is successful
     */
    boolean insertOneReview(UserReview userReview);

    /**
     * Remove one course ControlPresentInfo the user course list, the result is false if there is no such course ControlPresentInfo the course list.
     * Note that method should be called after authentication.
     *
     * @param courseCode course code
     * @return true iff the removal is successful.
     */
    boolean removeOneCourse(String courseCode);


    /**
     * Remove the one course review from the review list of targeted user.
     *
     * @param userReview user review entity
     * @return true iff the removal is successful
     */
    boolean removeOneReview(UserReview userReview);

    /**
     * Remove one course ControlPresentInfo the wish list, the result is false if there is no such course ControlPresentInfo the wish list.
     * Note that method should be called after the authentication.
     *
     * @param courseCode course code
     * @return true iff the removal is successful.
     */
    boolean removeOneWish(String courseCode);

    /**
     * Clear the course list.
     *
     * @return true iff the process is successful
     */
    boolean clearCourseList();

    /**
     * Clear the wish list.
     *
     * @return true iff the process is successful
     */
    boolean clearWishList();

    /**
     * Clear the schedule list.
     *
     * @return true iff the process is successful
     */
    boolean clearScheduleList();

    /**
     * Update the Schedule to the head of schedule list.
     *
     * @param schedule schedule
     * @return true iff the process is successful
     */
    boolean insertOneSchedule(Schedule schedule);
}
