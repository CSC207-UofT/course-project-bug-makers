package com.courseApp.reviewService;

import java.util.ArrayList;

public interface ControlPresentReview {
    /**
     * Get string summary of each InstReview under targeted course code.
     *  - should include:
     *      - name of the instructor
     *      - general rate
     *      - difficulty rate
     *
     * @param courseCode targeted course code
     * @return array list of String representation.
     */
    ArrayList<String> getInstReviewSummary(String courseCode);

    /**
     * Get string summary of each UserReview under targeted course code and instructor.
     *  - should include:
     *      - username
     *      - general rate
     *      - difficulty rate
     *  - note that it should not include the recommendation rate, as it's for internal use.
     *
     * @param courseCode targeted course code
     * @param instName targeted instructor name
     * @return array list of String representation.
     */
    ArrayList<String> getUserReviewSummary(String courseCode, String instName);

    /**
     * Get a list of course codes, that are in the database
     *
     * @return arraylist of strings
     */
    ArrayList<String> getExistingCourseList();
}
