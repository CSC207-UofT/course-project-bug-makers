package com.courseApp.dao;

import com.courseApp.entity.CourseReview;
import com.courseApp.entity.InstReview;
import com.courseApp.entity.UserReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface providing connection to database reviewSheet
 */
@Repository
public interface ReviewDAO extends MongoRepository<CourseReview, String> {

    /**
     * Query course review by given course code
     *
     * @param courseCode given course code (without section)
     * @return CourseReview Entity
     */
    CourseReview findByCourseCode(String courseCode);

    /**
     * Query existing course list in the review database.
     *
     * @return Arraylist of course code.
     */
    ArrayList<String> findAllCourseCode();


}
