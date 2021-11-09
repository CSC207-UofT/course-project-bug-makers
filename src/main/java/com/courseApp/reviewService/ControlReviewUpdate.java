package com.courseApp.reviewService;

public interface ControlReviewUpdate {

    /**
     * Delete the user review under the given username, note that the reviews under one instructor should have
     * unique username.
     *
     *
     * @param courseCode targeted course code
     * @param instName targeted instructor name
     * @param username username of the review to be deleted
     * @return true iff the deletion is successful
     */
    boolean deleteUserReview(String courseCode, String instName, String username);

    /**
     * Edit the user review under targeted instructor. (Delete + Insert the new one)
     *
     * Note that the recommendation score should come from the recommendationService.
     *
     * @param courseCode targeted course code
     * @param instName instructor name to be created
     * @param generalRate given general rate
     * @param difficultyRate given difficulty rate
     * @param reviewString given review string
     * @return ture iff the creation is successful
     */
    boolean editUserReview(String  courseCode,
                           String instName,
                           double generalRate,
                           double difficultyRate,
                           String reviewString);
}
