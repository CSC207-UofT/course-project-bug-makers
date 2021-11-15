package com.courseApp.dao;

import com.courseApp.constants.Constants;
import com.courseApp.entity.Schedule;
import com.courseApp.entity.User;
import com.courseApp.entity.UserReview;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

/**
 * Implemented User DAO for user data querying, login/register services.
 */
public class UserDaoImpl extends AbstractDatabaseDao implements UserDAO{
    private final String userName;
    private String password;
    private final MongoCollection<User> collection;
    private final Bson filter;


    /**
     * Constructor taking ControlPresentInfo username and password, for register/login service.
     *
     * @param username username
     * @param password password
     */
    public UserDaoImpl(String username, String password) {
        this.userName = username;
        this.password = password;
        this.collection = this.getCollection();
        this.filter = eq(Constants.USERNAME, this.userName);
    }

    /**
     * Constructor taking ControlPresentInfo username, for querying service. Note that this branch must be called
     * after the authentication is done.
     *
     * @param username username
     */
    public UserDaoImpl(String username) {
        this.userName = username;
        this.collection = this.getCollection();
        this.filter = eq(Constants.USERNAME, this.userName);
    }

    /**
     * Return a User obj, syncing with the database iff the password is correct.
     *
     * @return User obj iff password is correct
     */
    @Override
    public User queryUser() {
        if(checkPassword()){
            return collection.find(eq(Constants.USERNAME, this.userName)).first();
        }
        return null;
    }

    /**
     * Check if the username already exists ControlPresentInfo the DB.
     *
     * @return true iff the username is ControlPresentInfo the DB, otherwise, false
     */
    @Override
    public Boolean queryUserInDB() {
        return collection.find(this.filter).first() != null;

    }

    /**
     * Query User's role
     *
     * @return user's role
     */
    @Override
    public String queryUserRole() {
        return this.queryByUserName().getUserRole();
    }

    /**
     * Query user's course list
     *
     * @return user's course list
     */
    @Override
    public ArrayList<String> queryCourseList() {
        return this.queryByUserName().getCourseList();
    }

    /**
     * Query user's wish list
     *
     * @return user's wish list
     */
    @Override
    public ArrayList<String> queryWishList() {
        return this.queryByUserName().getWishList();

    }

    /**
     * Rewrite the course list in the database.
     *
     * @return user's schedule list with no schedule map ControlPresentInfo the schedule object.
     */
    @Override
    public ArrayList<Schedule> queryScheduleList() {
        return this.queryByUserName().getScheduleList();
    }

    /**
     * Update course list.
     *
     * @param courseList User course list
     * @return ture iff the update is successful
     */
    @Override
    public boolean updateCourseList(ArrayList<String> courseList) {
        this.collection.updateOne(this.filter, set(Constants.COURSE_LIST, courseList));
        return true;
    }

    /**
     * Rewrite the wish list in the database.
     *
     * @param wishList User wish list
     * @return ture iff the update is successful
     */
    @Override
    public boolean updateWishList(ArrayList<String> wishList) {
        this.collection.updateOne(this.filter, set(Constants.WISH_LIST, wishList));
        return true;

    }

    /**
     * Rewrite schedule list in the database.
     *
     * @param scheduleList list of schedule
     * @return ture iff the update is successful
     */
    @Override
    public boolean updateScheduleList(ArrayList<Schedule> scheduleList) {
        this.collection.updateOne(this.filter, set(Constants.SCHEDULE_LIST, scheduleList));
        return true;
    }

    /**
     * Query the user review list, corresponding to the targeted user.
     *
     * @return ArrayList of User Review entity.
     */
    @Override
    public ArrayList<UserReview> queryUserReviewList() {
        return this.queryByUserName().getReviewList();
    }

    /**
     * Rewrite the review list in the database
     *
     * @param reviewList list of UserReviews
     * @return true iff teh update is successful
     */
    @Override
    public boolean updateUserReviewList(ArrayList<UserReview> reviewList) {
        this.collection.updateOne(this.filter, set(Constants.REVIEW_LIST, reviewList));
        return true;
    }

    /**
     * User register service with user existence checked.
     *
     * @return true iff register is successful, otherwise, false
     */
    @Override
    public Boolean userRegister() {
        if (!queryUserInDB()){
            User user = new User(this.userName, this.password);
            this.collection.insertOne(user);
        return true;}
        return false;

    }

    /**
     * User Login service with password check and username check.
     *
     * @return ture iff login is successful, otherwise, false.
     */
    @Override
    public Boolean userLogin() {
        if (queryUserInDB()){return checkPassword();} return false;
    }


    /**
     * Query information by username.
     *
     * @return Iterator of username, note that we are only allowing one username to appear ControlPresentInfo the database for
     * once, so the queried item should be the first one ControlPresentInfo the iterator.
     */
    private User queryByUserName(){
        return collection.find(eq(Constants.USERNAME, this.userName)).first();
    }

    /**
     * Apply POJO to the userSheet collection by Bson.
     *
     * @return MongoDB's userSheet collection, which applied User entity POJOs support.
     */
    private MongoCollection<User> getCollection() {
        MongoDatabase mongoDb =  getDatabase();
        return mongoDb.getCollection(Constants.DB_USER_COLLECTION_NAME, User.class);
    }

    /**
     * Check the provided password with the one ControlPresentInfo the database.
     * Note that the encoding algorithm used is MD5.
     *
     * @return True iff the password is consistent, otherwise, false.
     */
    private boolean checkPassword(){
        return queryByUserName().checkPassword(this.password);
    }

    /**
     * Convert list of Schedule objs to list of sectionList obj.
     *
     * @param scheduleList schedule list
     * @return section list
     */
    @Deprecated
    private ArrayList<ArrayList<String>> scheduleListArray2sectionListArray(ArrayList<Schedule> scheduleList){
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(Schedule schedule: scheduleList){
            res.add(schedule.getSectionList());
        }
        return res;
    }


}
