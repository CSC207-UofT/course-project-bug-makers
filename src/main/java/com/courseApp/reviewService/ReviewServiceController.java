package com.courseApp.reviewService;

import com.courseApp.constants.Constants;

import java.util.*;

//TODO: entire doc
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
        StringBuilder result = new StringBuilder();
        ArrayList<String> InstReviewSummary = new ArrayList<>();

        for (String instName: new ReviewRequestProcessor().queryExistingInst(courseCode)){
            for (Map.Entry<String, String> entry: rrp.queryInstReviewSummary(courseCode, instName).entrySet()){
                result.append(Constants.TRI_TAB).append(Constants.INST_NAME).append(instName).append(Constants.CHANGE_LINE);
                if (entry.getKey().equals("instGeneralRate")){
                    String instGeneralRate = entry.getValue();
                    result.append(Constants.TRI_TAB).append(Constants.INST_GENERAL_RATE).append(instGeneralRate).append(Constants.CHANGE_LINE);
                }
                if (entry.getKey().equals("instDifficultyRate")){
                    String instDifficultyRate = entry.getValue();
                    result.append(Constants.TRI_TAB).append(Constants.INST_DIFFICULTY_RATE).append(instDifficultyRate).append(Constants.CHANGE_LINE);
                }

            }

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
        StringBuilder result = new StringBuilder();
        ArrayList<String> UserReviewSummary = new ArrayList<>();

        for (String username: new ReviewRequestProcessor().queryUsername(instName)){
            for (Map.Entry<String, String> entry: rrp.queryUserReview(courseCode, instName, username).entrySet()) {
                result.append(Constants.TRI_TAB).append(Constants.USERNAME).append(username).append(Constants.CHANGE_LINE);
                if (entry.getKey().equals("generalRate")) {
                    String generalRate = entry.getValue();
                    result.append(Constants.TRI_TAB).append(Constants.GENERAL_RATE).append(generalRate).append(Constants.CHANGE_LINE);
                }
                if (entry.getKey().equals("difficultyRate")) {
                    String difficultyRate = entry.getValue();
                    result.append(Constants.TRI_TAB).append(Constants.DIFFICULTY_RATE).append(difficultyRate).append(Constants.CHANGE_LINE);
                }
            }

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
        for (String instName: new ReviewRequestProcessor().queryExistingInst(courseCode)){
            return new ReviewRequestProcessor().createOneCourseReview(courseCode, instName);
        }
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
        return new ReviewRequestProcessor().createOneInstReview(courseCode, instName);
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
     * @return true iff the creation is successful
     */
    @Override
    public boolean createNewUserReview(String courseCode, String instName, double generalRate, double difficultyRate, String reviewString) {
        ReviewRequestProcessor rrp = new ReviewRequestProcessor();
        RecommendationRequestProcessor recommendationRR = new RecommendationRequestProcessor();

        for (String username: new ReviewRequestProcessor().queryUsername(instName)){
            return rrp.insertOneUserReview(courseCode, username,  instName, generalRate, difficultyRate, recommendationRR.modelInference(reviewString), reviewString);
        }
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
        return new ReviewRequestProcessor().deleteOneUserReview(courseCode, instName, username);
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
     * @return true iff the creation is successful
     */
    @Override
    public boolean editUserReview(String courseCode, String instName, double generalRate, double difficultyRate, String reviewString) {
        ReviewRequestProcessor rrp = new ReviewRequestProcessor();
        RecommendationRequestProcessor recommendationRR = new RecommendationRequestProcessor();

        for (String username: new ReviewRequestProcessor().queryUsername(instName)){
            rrp.deleteOneUserReview(courseCode, instName, username);
            return rrp.insertOneUserReview(courseCode, instName, username, generalRate, difficultyRate, recommendationRR.modelInference(reviewString), reviewString);
        }
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
