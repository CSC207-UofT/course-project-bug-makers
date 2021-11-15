package com.courseApp.reviewService;

import com.courseApp.constants.Constants;

import java.util.*;

/**
 * Review Service Controller for providing review service branch api.
 */
public class ReviewServiceController implements ControlReviewUpdate, ControlReviewCreation, ControlPresentReview, ControlRecommendationRank{

    /**
     * Get string summary of each InstReview under targeted course code.
     * - should include:
     * - name of the instructor
     * - general rate
     * - difficulty rate
     * @param courseCode targeted course code
     *
     * @return array list of String representation.
     */
    @Override
    public ArrayList<String> getInstReviewSummary(String courseCode) {

        ReviewRequestProcessor rrp = new ReviewRequestProcessor();
        ArrayList<String> InstReviewSummary = new ArrayList<>();

        for (String instName: new ReviewRequestProcessor().queryExistingInst(courseCode)){
            // loop over the existing instructor list.
            StringBuilder result = new StringBuilder();
            Map<String, String> entry = rrp.queryInstReviewSummary(courseCode, instName);
            result.append(Constants.INST_NAME).append(Constants.TRI_TAB).append(instName).append(Constants.TRI_TAB).append(Constants.CHANGE_LINE); // write name
            result.append(Constants.TRI_TAB).append(Constants.INST_GENERAL_RATE).append(Constants.TRI_TAB).append(entry.get(Constants.INST_GENERAL_RATE)).append(Constants.CHANGE_LINE); // write general rate
            result.append(Constants.TRI_TAB).append(Constants.INST_DIFFICULTY_RATE).append(Constants.TRI_TAB).append(entry.get(Constants.INST_DIFFICULTY_RATE)).append(Constants.CHANGE_LINE); // write difficulty rate
            result.append(Constants.LONG_LINE).append(Constants.CHANGE_LINE);
            InstReviewSummary.add(result.toString());
        }

    return InstReviewSummary;

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

        ReviewRequestProcessor rrp = new ReviewRequestProcessor();
        ArrayList<String> UserReviewSummary = new ArrayList<>();

        for (String username: new ReviewRequestProcessor().queryUsername(courseCode, instName)){
            // loop over the existing username list.
            StringBuilder result = new StringBuilder();
            Map<String, String> UserReview = rrp.queryUserReview(courseCode, instName,username);
            result.append(Constants.USERNAME).append(Constants.TRI_TAB).append(username).append(Constants.TRI_TAB).append(Constants.CHANGE_LINE); // write username
            result.append(Constants.TRI_TAB).append(Constants.GENERAL_RATE).append(Constants.TRI_TAB).append(UserReview.get(Constants.GENERAL_RATE)).append(Constants.CHANGE_LINE); // write general rate
            result.append(Constants.TRI_TAB).append(Constants.DIFFICULTY_RATE).append(Constants.TRI_TAB).append(UserReview.get(Constants.DIFFICULTY_RATE)).append(Constants.CHANGE_LINE); // write difficulty rate
            result.append(Constants.LONG_LINE).append(Constants.CHANGE_LINE);
            UserReviewSummary.add(result.toString());
        }

        return UserReviewSummary;

    }

    /**
     * Get a list of course codes, that are in the database
     *
     * @return arraylist of strings
     */
    @Override
    public ArrayList<String> getExistingCourseList() {
        return new ReviewRequestProcessor().queryExistingCourse();
    }

    /**
     * Validate the course code and then create a new course review.
     *
     * @param courseCode course code to be created
     * @return true iff the creation is successful
     */
    @Override
    public boolean createNewCourse(String courseCode) {
            return new ReviewRequestProcessor().createOneCourseReview(courseCode);
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
        return new ReviewRequestProcessor().createOneInstReview(courseCode, instName);
    }

    /**
     * Validate the username and then create a new user review under targeted instructor.
     * Note that the recommendation score should come from the recommendationService.
     *
     * @param courseCode     targeted course code
     * @param instName       instructor name to be created
     * @param generalRate    given general rate
     * @param difficultyRate given difficulty rate
     * @param reviewString   given review string
     * @return true iff the creation is successful
     */
    @Override
    public boolean createNewUserReview(String courseCode, String instName, String username, double generalRate, double difficultyRate, String reviewString) throws Exception {
        ReviewRequestProcessor rrp = new ReviewRequestProcessor();
        RecommendationRequestProcessor recommendationRR = new RecommendationRequestProcessor();

        return rrp.insertOneUserReview(courseCode, instName, username, generalRate, difficultyRate, recommendationRR.modelInference(reviewString), reviewString);
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
        return new ReviewRequestProcessor().deleteOneUserReview(courseCode, instName, username);
    }

    /**
     * Edit the user review under targeted instructor. (Delete + Insert the new one)
     * Note that the recommendation score should come from the recommendationService.
     *
     * @param courseCode     targeted course code
     * @param instName       instructor name to be created
     * @param generalRate    given general rate
     * @param difficultyRate given difficulty rate
     * @param reviewString   given review string
     * @return true iff the creation is successful
     */
    @Override
    public boolean editUserReview(String courseCode, String instName, String username, double generalRate, double difficultyRate, String reviewString) throws Exception {
        ReviewRequestProcessor rrp = new ReviewRequestProcessor();
        RecommendationRequestProcessor recommendationRR = new RecommendationRequestProcessor();

        rrp.deleteOneUserReview(courseCode, instName, username);
        return rrp.insertOneUserReview(courseCode, instName, username, generalRate, difficultyRate, recommendationRR.modelInference(reviewString), reviewString);
    }

    /**
     * Get the arraylist of ranked instructor names for targeted course.
     * The arraylist should be in decreasing order of recommendation index, which the first inst in the list should be
     * the most recommended and the last one should be least recommended.
     *
     * @param courseCode given course code
     * @return array list of instructor name.
     */
    @Override
    public ArrayList<String> getInstRank(String courseCode) {
        ReviewRequestProcessor rrp = new ReviewRequestProcessor();
        RecommendationRequestProcessor recommendationRR = new RecommendationRequestProcessor();
        ArrayList<String> InstRank = new ArrayList<>();

        LinkedHashMap<String, Double> instRank = new LinkedHashMap<>();
        recommendationRR.generateComplexScoreMap(rrp.getRecommendationMap(courseCode)).
                entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> instRank.put(x.getKey(), x.getValue()));

        InstRank.add(instRank.toString());

        return InstRank;
    }
}
