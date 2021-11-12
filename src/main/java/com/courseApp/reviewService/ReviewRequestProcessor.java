package com.courseApp.reviewService;

import com.courseApp.dao.ReviewDAO;
import com.courseApp.dao.ReviewDaoImpl;
import com.courseApp.entity.CourseReview;
import com.courseApp.entity.InstReview;
import com.courseApp.entity.UserReview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        ReviewDAO reviewDao = new ReviewDaoImpl();
        CourseReview courseReview = reviewDao.queryCourseReview(courseCode);
        if (courseReview != null){

            // Create map to store summary values
            Map<String, String> summary = new HashMap<>();

            // Add keys and values to the summary map
            summary.put("courseCode", courseCode);
            summary.put("courseDifficultyRate",
                    String.valueOf(courseReview.getCourseDifficultyRate()));
            summary.put("courseGeneralRate",
                    String.valueOf(courseReview.getCourseGeneralRate()));

            // return the summary map
            return summary;
        } else {
            return null;
        }
    }

    /**
     * Check the existence of the instructor review, and then return the instructor review summary map, or null if the
     * instructor entity is not in the database
     * <p>
     * The summary map should be:
     * instructorName: String
     * instDifficultyRate: String
     * instGeneralRate: String
     *
     * @param courseCode course code w/o section flag
     * @param instName   Instructor name
     * @return InstReview summary map if targeted instructor is in the database, otherwise, null
     */
    @Override
    public Map<String, String> queryInstReviewSummary(String courseCode, String instName) {
        ReviewDAO reviewDao = new ReviewDaoImpl();
        InstReview instReview = reviewDao.queryInstReview(courseCode, instName);
        if (instReview != null){

            // Create map to store summary values
            Map<String, String> summary = new HashMap<>();

            // Add keys and values to the summary map
            summary.put("instructorName", instName);
            summary.put("instDifficultyRate",
                    String.valueOf(instReview.getInstDifficultyRate()));
            summary.put("courseGeneralRate",
                    String.valueOf(instReview.getInstGeneralRate()));

            // return the summary map
            return summary;
        } else {
            return null;
        }
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
        ReviewDAO reviewDao = new ReviewDaoImpl();
        UserReview userReview = reviewDao.queryUserReview(courseCode, instName, username);
        if (userReview != null){
            // Create map to store summary values
            Map<String, String> summary = new HashMap<>();

            // Add keys and values to the summary map
            summary.put("username", username);
            summary.put("instDifficultyRate",
                    String.valueOf(userReview.getDifficultyRate()));
            summary.put("generalRate",
                    String.valueOf(userReview.getGeneralRate()));
            summary.put("reviewString", userReview.getReviewString());

            // return the summary map
            return summary;
        } else {
            return null;
        }
    }

    /**
     * Query existing course list in the review database.
     *
     * @return Arraylist of course code.
     */
    @Override
    public ArrayList<String> queryExistingCourse() {
        return new ReviewDaoImpl().queryExistingCourse();
    }

    /**
     * Query existing instructor name under targeted course.
     *
     * @param courseCode targeted courseCode
     * @return Arraylist of instructor's name
     */
    @Override
    public ArrayList<String> queryExistingInst(String courseCode) {
        //TODO: Finish method
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
        ReviewDAO reviewDAO = new ReviewDaoImpl();

        // Create new User Review using given parameters
        UserReview newUserReview =
                new UserReview(username, generalRate, difficultyRate, recommendationRate, reviewString);

        // Create a new ArrayList to store the new User Review and add the User Review to the ArrayList
        ArrayList<UserReview> UserReviewList = new ArrayList<>();
        UserReviewList.add(newUserReview);

        // Update the user's UserReviewList and return true iff insertion is successful
        return reviewDAO.UpdateUserReviewList(courseCode, instName, UserReviewList);
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
        ReviewDAO reviewDAO = new ReviewDaoImpl();
        //TODO: Finish method
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
        return new ReviewDaoImpl().createInstReview(courseCode, instName);
    }

    /**
     * Create one new course review section under targeted course.
     *
     * @param courseCode targeted course
     * @return true iff the creation is successful
     */
    @Override
    public boolean createOneCourseReview(String courseCode, String instName) {
        return new ReviewDaoImpl().createCourseReview(courseCode);
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
        //TODO: Finish method
        return null;
    }
}
