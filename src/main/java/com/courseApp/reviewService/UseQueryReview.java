package com.courseApp.reviewService;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface for realizing review data query.
 *
 */
public interface UseQueryReview {

    /**
     * Check the existence of course review, and then return the course review summary map, or null if the course review is
     * not in the database.
     *
     * The summary map should be:
     *  courseCode: String
     *  courseDifficultyRate: String
     *  courseGeneralRate: String
     *
     * @param courseCode course code w/o section flag
     * @return CourseReview summary map if targeted course in the database, otherwise, null
     */
    Map<String, String> queryCourseReviewSummary(String courseCode);

    /**
     * Check the existence of the instructor review, and then return the instructor review summary map, or null if the
     * instructor entity is not in the database
     *
     * The summary map should be:
     *  instructorName: String
     *  instDifficultyRate: String
     *  instDifficultyRate: String
     *
     * @param courseCode course code w/o section flag
     * @param instName Instructor name
     * @return InstReview summary map if targeted instructor is in the database, otherwise, null
     */
    Map<String, String> queryInstReviewSummary(String courseCode, String instName);

    /**
     * Check the existence of the user review, and then return the user review summary map, or null if the
     * instructor entity is not in the database
     *
     * The summary map should be:
     *  username: String
     *  generalRate: String
     *  difficultyRate: String
     *  reviewString: String
     *
     * @param courseCode course code w/o section flag
     * @param instName Instructor name
     * @param username  username
     * @return UserReview summary map if targeted user review is in the database, otherwise, null
     */
    Map<String, String> queryUserReview(String courseCode, String instName, String username);

    /**
     * Query existing courses in the review database.
     *
     * @return Arraylist of course code.
     */
    ArrayList<String> queryExistingCourse();

    /**
     * Query existing instructor name under targeted course.
     *
     * @param courseCode targeted courseCode
     *
     * @return Arraylist of instructor's name
     */
    ArrayList<String> queryExistingInst(String courseCode);

    /**
     * Query existing username name under specific instructor.
     *
     * @param instName   Instructor name
     * @param courseCode Course code
     *
     * @return Arraylist of username's
     */
    ArrayList<String> queryUsername(String courseCode, String instName);



}
