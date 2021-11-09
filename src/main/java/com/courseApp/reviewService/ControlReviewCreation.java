package com.courseApp.reviewService;

public interface ControlReviewCreation {

    /**
     * Validate the course code and then create a new course review.
     *
     *
     * @param courseCode course code to be created
     * @return true iff the creation is successful
     */
    boolean createNewCourse(String courseCode);

    /**
     * Validate the instructor name and then create a new instructor review under targeted course code.
     *
     * @param courseCode targeted course code
     * @param instName instructor name to be created
     * @return true iff the creation is successful
     */
    boolean createNewInst(String courseCode, String instName);

    /**
     * Validate the username and then create a new user review under targeted instructor.
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
    boolean createNewUserReview(String  courseCode,
                                String instName,
                                double generalRate,
                                double difficultyRate,
                                String reviewString);
}
