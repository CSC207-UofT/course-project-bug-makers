package com.courseApp.dao;

import com.courseApp.constants.Constants;
import com.courseApp.entity.CourseReview;
import com.courseApp.entity.InstReview;
import com.courseApp.entity.UserReview;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

/**
 * Implemented Review DAO for user data querying, IO services.
 */
public class ReviewDaoImpl extends AbstractDatabaseDao implements ReviewDAO{

    private final MongoCollection<CourseReview > collection;

    public ReviewDaoImpl() {
        this.collection = getCollection();
    }

    /**
     * Query course review by given course code
     *
     * @param courseCode given course code (without section)
     * @return CourseReview Entity
     */
    @Override
    public CourseReview queryCourseReview(String courseCode) {
        return collection.find(eq(Constants.COURSE_CODE_DB, courseCode)).first();
    }

    /**
     * Query existing course list in the review database.
     *
     * @return Arraylist of course code.
     */
    @Override
    public ArrayList<String> queryExistingCourse() {
        ArrayList<CourseReview> list = collection.find().into(new ArrayList<>());
        ArrayList<String> res = new ArrayList<>();
        for(CourseReview cr : list){
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
    @Override
    public InstReview queryInstReview(String courseCode, String instName) {
        return Objects.requireNonNull(collection.find(eq(Constants.COURSE_CODE_DB,
                courseCode)).first()).getSpecificInstReview(instName);
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
    @Override
    public UserReview queryUserReview(String courseCode, String instName, String username) {
        return Objects.requireNonNull(collection.find(eq(Constants.COURSE_CODE_DB,
                courseCode)).first()).getSpecificInstReview(instName).getSpecificUserReview(username);
    }

    /**
     * Update user review list to the targeted instructor review under given course code.
     *
     * @param courseCode     given course code
     * @param instName       given instructor name
     * @param userReviewList user review to be created
     * @return true iff update is successful
     */
    @Override
    public boolean UpdateUserReviewList(String courseCode, String instName, ArrayList<UserReview> userReviewList) {
        CourseReview cr = this.queryCourseReview(courseCode);
        cr.getSpecificInstReview(instName).setUserReviewList(userReviewList);
        collection.updateOne(eq(Constants.COURSE_CODE_DB, courseCode),
                combine(set(Constants.INST_REVIEW_MAP, cr.getInstReviewMap()),
                set(Constants.COURSE_GENERAL_RATE, cr.getCourseGeneralRate()),
                set(Constants.COURSE_DIFFICULTY_RATE, cr.getCourseDifficultyRate())));
        return true;
    }

    /**
     * Create an instructor review sheet under targeted course code
     *
     * @param courseCode targeted course code
     * @param instName   targeted instructor name
     * @return true iff creation is successful
     */
    @Override
    public boolean createInstReview(String courseCode, String instName) {
        CourseReview cr = this.queryCourseReview(courseCode);
        cr.createNewInstReview(instName);
        collection.updateOne(eq(Constants.COURSE_CODE_DB, courseCode),
                combine(set(Constants.INST_REVIEW_MAP, cr.getInstReviewMap()),
                        set(Constants.INST_LIST, cr.getInstList() )));
        return true;
    }

    /**
     * Create a course review sheet for targeted course code
     *
     * @param courseCode targeted course code
     * @return true iff creation is successful
     */
    @Override
    public boolean createCourseReview(String courseCode) {
        collection.insertOne(new CourseReview(courseCode));
        return true;
    }

    private MongoCollection<CourseReview> getCollection(){
        MongoDatabase mongoDb = getDatabase();
        return mongoDb.getCollection(Constants.DB_REVIEW_COLLECTION_NAME, CourseReview.class);
    }


//    public static void main(String[] args) {
//        ReviewDaoImpl rdi = new ReviewDaoImpl();
//        rdi.createCourseReview("CSC207");
//        rdi.createInstReview("CSC207", "test");
//        UserReview ur = new UserReview("username", 1.0D, 1.0D, 1.0D, "GOOD");
//        ArrayList<UserReview> urList = new ArrayList<>();
//        urList.add(ur);
//        urList.add(ur);
//        rdi.UpdateUserReviewList("CSC207", "test", urList);
//        System.out.println(rdi.queryExistingCourse());
//    }
}

