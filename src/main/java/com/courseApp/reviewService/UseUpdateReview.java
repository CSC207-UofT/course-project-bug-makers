package com.courseApp.reviewService;

import com.courseApp.entity.UserReview;

/**
 * Interface for realizing review update.
 *
 */
public interface UseUpdateReview {


    /**
     * Inset one user review entity to targeted course and instructor.
     *
     * Note that the recommendation rate will be updated by model inference.
     *
     * @param courseCode courseCode targeted course
     * @param instName instName targeted instructor
     * @param username username
     * @param generalRate given general rate
     * @param difficultyRate given difficulty rate
     * @param reviewString given review
     * @param recommendationRate given recommendation rate
     * @return true iff the insertion is successful
     */
    boolean insertOneUserReview(String courseCode, String instName,
                                String username,
                                double generalRate,
                                double difficultyRate,
                                double recommendationRate,
                                String reviewString);

    /**
     * Delete one user review entity to targeted course and instructor. Note that the reviews under one instructor should have
     * unique username.
     *
     *
     * @param courseCode targeted course
     * @param instName targeted instructor
     * @param username username of the user review to be deleted
     * @return true iff the deletion is successful
     */
    boolean deleteOneUserReview(String courseCode, String instName, String username);


    /**
     * Create one new instructor review section under targeted course.
     *
     * @param courseCode targeted course
     * @param instName instructor name to be created
     * @return true iff the creation is successful
     */
    boolean createOneInstReview(String courseCode, String instName);

    /**
     * Create one new course review section under targeted course.
     *
     * @param courseCode targeted course
     * @return true iff the creation is successful
     */
    boolean createOneCourseReview(String courseCode);
}
