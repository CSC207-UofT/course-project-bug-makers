package com.courseApp.dao;

import com.courseApp.constants.Constants;
import com.courseApp.entity.CourseReview;
import com.courseApp.entity.InstReview;
import com.courseApp.entity.UserReview;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

/**
 * Implemented Review DAO for user data querying, IO services.
 */
@Repository
@EnableMongoRepositories(basePackageClasses =  CourseReview.class)
public class ReviewDaoImpl{


    ReviewDAO reviewDao;

    /**
     * Default constructor for spring boot, auto-injection
     */
    @Autowired
    public ReviewDaoImpl() {
    }

    /**
     * Query course review by given course code
     *
     * @param courseCode given course code (without section)
     * @return CourseReview Entity
     */
    public CourseReview queryCourseReview(String courseCode) {
        return reviewDao.findByCourseCode(courseCode);
    }

    /**
     * Query existing course list in the review database.
     *
     * @return Arraylist of course code.
     */
    public ArrayList<String> queryExistingCourse() {
        List<CourseReview> crList = reviewDao.findAll();
        ArrayList<String> res = new ArrayList<>();
        for(CourseReview cr: crList){
            res.add(cr.getCourseCode());
        }
        return res;
    }

    /**
     * Query Instructor Review by given course code and instructor name
     *
     * @param courseCode given course code (without section)
     * @param instName   given instructor name
     * @return InstReview Entity
     */
    public InstReview queryInstReview(String courseCode, String instName) {
        return queryCourseReview(courseCode).getInstReviewMap().get(instName);
    }

    /**
     * Query User Review by given course code, instructor name and username
     * Note that, one user shall only write one per instructor.
     * So there should be only one review under each username per instructor.
     *
     * @param courseCode given course code (without section)
     * @param instName   given instructor name
     * @param username   given username
     * @return UserReview
     */
    public UserReview queryUserReview(String courseCode, String instName, String username) {
        return queryInstReview(courseCode, instName).getSpecificUserReview(username);
    }

    /**
     * Update user review list to the targeted instructor review under given course code.
     *
     * @param courseCode     given course code
     * @param instName       given instructor name
     * @param userReviewList user review to be created
     * @return true iff update is successful
     */
    public boolean UpdateUserReviewList(String courseCode, String instName, ArrayList<UserReview> userReviewList) {
        CourseReview cr = queryCourseReview(courseCode);
        cr.getInstReviewMap().get(instName).setUserReviewList(userReviewList);
        reviewDao.save(cr);
        return true;
    }

    /**
     * Create an instructor review sheet under targeted course code
     *
     * @param courseCode targeted course code
     * @param instName   targeted instructor name
     * @return true iff creation is successful
     */
    public boolean createInstReview(String courseCode, String instName) {
        CourseReview cr = queryCourseReview(courseCode);
        cr.createNewInstReview(instName);
        reviewDao.save(cr);
        return true;
    }

    /**
     * Create a course review sheet for targeted course code
     *
     * @param courseCode targeted course code
     * @return true iff creation is successful
     */
    public boolean createCourseReview(String courseCode) {
        reviewDao.save(new CourseReview(courseCode));
        return true;
    }

}

