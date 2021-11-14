package com.courseApp.reviewService;

import com.courseApp.dao.ReviewDAO;
import com.courseApp.dao.ReviewDaoImpl;
import com.courseApp.entity.CourseReview;
import com.courseApp.entity.InstReview;
import com.courseApp.entity.UserReview;
import com.courseApp.constants.Constants;

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
            summary.put(Constants.COURSE_CODE_DB, courseCode);
            summary.put(Constants.COURSE_DIFFICULTY_RATE,
                    String.valueOf(courseReview.getCourseDifficultyRate()));
            summary.put(Constants.COURSE_GENERAL_RATE,
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
     * instGeneralRate: String
     * instDifficultyRate: String
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
            summary.put(Constants.INST_NAME, instName);
            summary.put(Constants.INST_DIFFICULTY_RATE,
                    String.valueOf(instReview.getInstDifficultyRate()));
            summary.put(Constants.INST_GENERAL_RATE,
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
            summary.put(Constants.USERNAME, username);
            summary.put(Constants.DIFFICULTY_RATE,
                    String.valueOf(userReview.getDifficultyRate()));
            summary.put(Constants.GENERAL_RATE,
                    String.valueOf(userReview.getGeneralRate()));
            summary.put(Constants.REVIEW_STRING, userReview.getReviewString());

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
        return new CourseReview(courseCode).getInstList();
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

        // Query the instReview for the given courseCode and instName
        InstReview InstReview = reviewDAO.queryInstReview(courseCode, instName);

        // Get the specific review of the instructor given a username
        UserReview userReview = InstReview.getSpecificUserReview(username);

        // Get the list of reviews of the instructor from InstReview
        ArrayList<UserReview> userReviews = InstReview.getUserReviewList();

        // Check to see if the review is in the reviews list
        // if true, then remove the review and update the instructor's review list
        if (userReviews.contains(userReview)){
            userReviews.remove(userReview);
            reviewDAO.UpdateUserReviewList(courseCode, instName, userReviews);
            return true;
        }
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
    public boolean createOneCourseReview(String courseCode) {
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
        ReviewDAO reviewDAO = new ReviewDaoImpl();

        // Create a Map, where key is the instName and value is a Map of the instructor's ratings
        Map<String, Map<String, Double>> recommendationMap = new HashMap<>();

        // Query the courseReview given the courseCode
        CourseReview courseReview = reviewDAO.queryCourseReview(courseCode);

        // Use the courseReview to get a list of instructors for the course
        ArrayList<String> instructors = courseReview.getInstList();

        // Iterate through the list of instructors
        for (String instructor: instructors){

            // Query the instructor's review given the course code and instName
            InstReview instReview = reviewDAO.queryInstReview(courseCode, instructor);

            // Create a new Map to store the instructor's ratings
            Map<String, Double> instructorRates = new HashMap<>();

            // Add the instructor's ratings to the map
            instructorRates.put(Constants.GENERAL_RATE, instReview.getInstGeneralRate());
            instructorRates.put(Constants.DIFFICULTY_RATE, instReview.getInstDifficultyRate());
            instructorRates.put(Constants.RECOMMENDATION_SCORE, instReview.getInstRecommendationScore());

            // Add the map of ratings to the recommendation map, with the key being the instName, and values being
            // the map of ratings
            recommendationMap.put(instructor, instructorRates);
        }
        return recommendationMap;
    }
}

