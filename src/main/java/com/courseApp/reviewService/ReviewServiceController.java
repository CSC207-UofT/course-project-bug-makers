package com.courseApp.reviewService;

import java.util.ArrayList;

//TODO: entire doc
public class ReviewServiceController implements ControlReviewUpdate, ControlReviewCreation, ControlPresentReview, ControlRecommendationRank{

    /**
     * Get string summary of each InstReview under targeted course code.
     * - should include:
     * - name of the instructor
     * - general rate
     * - difficulty rate
     *
     * @param courseCode targeted course code
     * @return array list of String representation.
     */
    @Override
    public ArrayList<String> getInstReviewSummary(String courseCode) {
        return null;
    }

    /**
     * Get string summary of each UserReview under targeted course code and instructor.
     * - should include:
     * - username
     * - general rate
     * - difficulty rate
     * - note that it should not include the recommendation rate, as it's for internal use.
     *
     * @param courseCode targeted course code
     * @param instName   targeted instructor name
     * @return array list of String representation.
     */
    @Override
    public ArrayList<String> getUserReviewSummary(String courseCode, String instName) {
        return null;
    }

    /**
     * Get a list of course codes, that are in the database
     *
     * @return arraylist of strings
     */
    @Override
    public ArrayList<String> getExistingCourseList() {
        return null;
    }

    /**
     * Validate the course code and then create a new course review.
     *
     * @param courseCode course code to be created
     * @return true iff the creation is successful
     */
    @Override
    public boolean createNewCourse(String courseCode) {
        return false;
    }

    /**
     * Validate the instructor name and then create a new instructor review under targeted course code.
     *
     * @param courseCode targeted course code
     * @param instName   instructor name to be created
     * @return true iff the creation is successful
     */
    @Override
    public boolean createNewInst(String courseCode, String instName) {
        return false;
    }

    /**
     * Validate the username and then create a new user review under targeted instructor.
     * <p>
     * Note that the recommendation score should come from the recommendationService.
     *
     * @param courseCode     targeted course code
     * @param instName       instructor name to be created
     * @param generalRate    given general rate
     * @param difficultyRate given difficulty rate
     * @param reviewString   given review string
     * @return ture iff the creation is successful
     */
    @Override
    public boolean createNewUserReview(String courseCode, String instName, double generalRate, double difficultyRate, String reviewString) {
        return false;
    }

    /**
     * Delete the user review under the given username, note that the reviews under one instructor should have
     * unique username.
     *
     * @param courseCode targeted course code
     * @param instName   targeted instructor name
     * @param username   username of the review to be deleted
     * @return true iff the deletion is successful
     */
    @Override
    public boolean deleteUserReview(String courseCode, String instName, String username) {
        return false;
    }

    /**
     * Edit the user review under targeted instructor. (Delete + Insert the new one)
     * <p>
     * Note that the recommendation score should come from the recommendationService.
     *
     * @param courseCode     targeted course code
     * @param instName       instructor name to be created
     * @param generalRate    given general rate
     * @param difficultyRate given difficulty rate
     * @param reviewString   given review string
     * @return ture iff the creation is successful
     */
    @Override
    public boolean editUserReview(String courseCode, String instName, double generalRate, double difficultyRate, String reviewString) {
        return false;
    }

    /**
     * Get the arraylist of ranked instructor names for targeted course.
     * <p>
     * The arraylist should be in decreasing order of recommendation index, which the first inst in the list should be
     * the most recommended and the last one should be least recommended.
     *
     * @param courseCode given course code
     * @return array list of instructor name.
     */
    @Override
    public ArrayList<String> getInstRank(String courseCode) {
        return null;
    }
}
