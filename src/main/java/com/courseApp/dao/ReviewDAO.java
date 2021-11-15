package com.courseApp.dao;

import com.courseApp.entity.CourseReview;
import com.courseApp.entity.InstReview;
import com.courseApp.entity.UserReview;

import java.util.ArrayList;

/**
 * Interface providing connection to database reviewSheet
 */
@SuppressWarnings("ALL")
public interface ReviewDAO {

    /**
     * Query course review by given course code
     *
     * @param courseCode given course code (without section)
     * @return CourseReview Entity
     */
    CourseReview queryCourseReview(String courseCode);


    /**
     * Query existing course list in the review sheet.
     *
     * @return Arraylist of course code.
     */
    ArrayList<String> queryExistingCourse();


    /**
     * Query Instructor Review by given course code and instructor name
     *
     * @param courseCode given course code (without section)
     * @param instName given instructor name
     * @return InstReview Entity
     */
    InstReview queryInstReview(String courseCode, String instName);

    /**
     * Query User Review by given course code, instructor name and username
     *
     * Note that, one user shall only write one per instructor.
     * So there should be only one review under each username per instructor.
     *
     * @param courseCode given course code (without section)
     * @param instName given instructor name
     * @param username given username
     * @return UserReview
     */
    UserReview queryUserReview(String courseCode, String instName, String username);

    /**
     * Update user review list to the targeted instructor review under given course code.
     *
     * @param courseCode given course code
     * @param instName given instructor name
     * @param userReviewList user review to be created
     * @return true iff update is successful
     */
    boolean UpdateUserReviewList(String courseCode, String instName, ArrayList<UserReview> userReviewList);

    /**
     * Create an instructor review sheet under targeted course code
     *
     * @param courseCode targeted course code
     * @param instName targeted instructor name
     * @return true iff creation is successful
     */
    boolean createInstReview(String courseCode, String instName);

    /**
     * Create a course review sheet for targeted course code
     *
     * @param courseCode targeted course code
     * @return true iff creation is successful
     */
    boolean createCourseReview(String courseCode);

}
