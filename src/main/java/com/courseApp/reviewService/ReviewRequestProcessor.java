package com.courseApp.reviewService;

import java.util.ArrayList;
import java.util.Map;

//TODO: entire doc
/**
 * Review Request Processor use case for realizing review data query and IO.
 */
public class ReviewRequestProcessor implements UseQueryReview, UseUpdateReview, UseQueryRecommendationInfo{

    /**
     * Check the existence of course review, and then return the course review summary map, or null if the course review is
     * not in the database.
     * <p>
     * The summary map should be:
     * courseCode: String
     * courseDifficultyRate: String
     * courseGeneralRate: String
     *
     * @param courseCode course code w/o section flag
     * @return CourseReview summary map if targeted course in the database, otherwise, null
     */
    @Override
    public Map<String, String> queryCourseReviewSummary(String courseCode) {
        return null;
    }

    /**
     * Check the existence of the instructor review, and then return the instructor review summary map, or null if the
     * instructor entity is not in the database
     * <p>
     * The summary map should be:
     * instructorName: String
     * instGeneralRate: String
     * instDifficultyRate: String
     *
     * @param courseCode course code w/o section flag
     * @param instName   Instructor name
     * @return InstReview summary map if targeted instructor is in the database, otherwise, null
     */
    @Override
    public Map<String, String> queryInstReviewSummary(String courseCode, String instName) {
        return null;
    }

    /**
     * Check the existence of the user review, and then return the user review summary map, or null if the
     * instructor entity is not in the database
     * <p>
     * The summary map should be:
     * username: String
     * generalRate: String
     * difficultyRate: String
     * reviewString: String
     *
     * @param courseCode course code w/o section flag
     * @param instName   Instructor name
     * @param username   username
     * @return UserReview summary map if targeted user review is in the database, otherwise, null
     */
    @Override
    public Map<String, String> queryUserReview(String courseCode, String instName, String username) {
        return null;
    }

    /**
     * Query existing course list in the review database.
     *
     * @return Arraylist of course code.
     */
    @Override
    public ArrayList<String> queryExistingCourse() {
        return null;
    }

    /**
     * Query existing instructor name under targeted course.
     *
     * @param courseCode targeted courseCode
     * @return Arraylist of instructor's name
     */
    @Override
    public ArrayList<String> queryExistingInst(String courseCode) {
        return null;
    }

    /**
     * Query existing username name under specific instructor.
     *
     * @param instName   Instructor name
     * @return Arraylist of username's
     */
    @Override
    public ArrayList<String> queryUsername(String instName) {
        return null;
    }

    /**
     * Insert one user review entity to targeted course and instructor.
     * <p>
     * Note that the recommendation rate will be updated by <></>.
     *
     * @param courseCode         courseCode targeted course
     * @param instName           instName targeted instructor
     * @param username           username
     * @param generalRate        given general rate
     * @param difficultyRate     given difficulty rate
     * @param recommendationRate given recommendation rate
     * @param reviewString       given review
     * @return true iff the insertion is successful
     */
    @Override
    public boolean insertOneUserReview(String courseCode, String instName, String username, double generalRate, double difficultyRate, double recommendationRate, String reviewString) {
        return false;
    }

    /**
     * Delete one user review entity to targeted course and instructor. Note that the reviews under one instructor should have
     * unique username.
     *
     * @param courseCode targeted course
     * @param instName   targeted instructor
     * @param username   username of the user review to be deleted
     * @return true iff the deletion is successful
     */
    @Override
    public boolean deleteOneUserReview(String courseCode, String instName, String username) {
        return false;
    }


    /**
     * Create one new instructor review section under targeted course.
     *
     * @param courseCode targeted course
     * @param instName   instructor name to be created
     * @return true iff the creation is successful
     */
    @Override
    public boolean createOneInstReview(String courseCode, String instName) {
        return false;
    }

    /**
     * Create one new instructor review section under targeted course.
     *
     * @param courseCode targeted course
     * @param instName   instructor name to be created
     * @return true iff the creation is successful
     */
    @Override
    public boolean createOneCourseReview(String courseCode, String instName) {
        return false;
    }

    /**
     * Get the recommendation map for a course, where the map should be:
     * String instName:
     * - generalRate: Double
     * - difficultyRate: Double
     * - recommendationScore : Double
     *
     * @param courseCode course code
     * @return recommendation map
     */
    @Override
    public Map<String, Map<String, Double>> getRecommendationMap(String courseCode) {
        return null;
    }
}
