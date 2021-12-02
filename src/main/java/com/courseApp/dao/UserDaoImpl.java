package com.courseApp.dao;

import com.courseApp.constants.Constants;
import com.courseApp.entity.Schedule;
import com.courseApp.entity.User;
import com.courseApp.entity.UserReview;
import com.courseApp.utils.PasswordEncoderMD5;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

/**
 * Implemented User DAO for user data querying, login/register services.
 */
@Repository
@EnableMongoRepositories(basePackageClasses = UserDAO.class)
public class UserDaoImpl {
    private  String userName;
    private String password;

    UserDAO userDao;


    /**
     * Default constructor for Spring boot.
     *
     */
    @Autowired
    public UserDaoImpl() { }

    /**
     * Constructor taking ControlPresentInfo username and password, for register/login service.
     *
     * @param username username
     * @param password raw password
     */
    public UserDaoImpl(String username, String password) {
        this.userName = username;
        this.password = password;
    }


    /**
     * Constructor taking ControlPresentInfo username, for querying service. Note that this branch must be called
     * after the authentication is done.
     *
     * @param username username
     */
    public UserDaoImpl(String username) {
        this.userName = username;
    }

    /**
     * Return a User obj, syncing with the database iff the password is correct.
     *
     * @return User obj iff password is correct
     */
    public User queryUser() {

        return userDao.findByUsernameAndEncryptedPassword(this.userName, PasswordEncoderMD5.encode(this.password));
    }

    /**
     * Check if the username already exists in the DB.
     *
     * @return true iff the username is in the DB, otherwise, false
     */
    public Boolean queryUserInDB() {
        return userDao.existsByUsername(this.userName);

    }

    /**
     * Query User's role
     *
     * @return user's role
     */
    public String queryUserRole() {
        return this.queryByUserName().getUserRole();
    }

    /**
     * Query user's course list
     *
     * @return user's course list
     */
    public ArrayList<String> queryCourseList() {
        return this.queryByUserName().getCourseList();
    }

    /**
     * Query user's wish list
     *
     * @return user's wish list
     */
    public ArrayList<String> queryWishList() {
        return this.queryByUserName().getWishList();

    }

    /**
     * Rewrite the course list in the database.
     *
     * @return user's schedule list with no schedule map ControlPresentInfo the schedule object.
     */
    public ArrayList<Schedule> queryScheduleList() {
        return this.queryByUserName().getScheduleList();
    }

    /**
     * Update course list.
     *
     * @param courseList User course list
     * @return ture iff the update is successful
     */
    public boolean updateCourseList(ArrayList<String> courseList) {
        User user = userDao.findByUsername(this.userName);
        user.setCourseList(courseList);
        userDao.save(user);
        return true;
    }

    /**
     * Rewrite the wish list in the database.
     *
     * @param wishList User wish list
     * @return ture iff the update is successful
     */
    public boolean updateWishList(ArrayList<String> wishList) {
        User user = userDao.findByUsername(this.userName);
        user.setWishList(wishList);
        userDao.save(user);
        return true;
    }

    /**
     * Rewrite schedule list in the database.
     *
     * @param scheduleList list of schedule
     * @return ture iff the update is successful
     */
    public boolean updateScheduleList(ArrayList<Schedule> scheduleList) {
        User user = userDao.findByUsername(this.userName);
        user.setScheduleList(scheduleList);
        userDao.save(user);
        return true;
    }

    /**
     * Query the user review list, corresponding to the targeted user.
     *
     * @return ArrayList of User Review entity.
     */
    public ArrayList<UserReview> queryUserReviewList() {
        return this.queryByUserName().getReviewList();
    }

    /**
     * Rewrite the review list in the database
     *
     * @param reviewList list of UserReviews
     * @return true iff teh update is successful
     */
    public boolean updateUserReviewList(ArrayList<UserReview> reviewList) {
        User user = userDao.findByUsername(this.userName);
        user.setReviewList(reviewList);
        userDao.save(user);
        return true;
    }

    /**
     * User register service with user existence checked.
     *
     * @return true iff register is successful, otherwise, false
     */
    public Boolean userRegister() {
        if (!queryUserInDB()){
            User user = new User(this.userName, this.password);
            userDao.save(user);
            return true;}
        return false;

    }

    /**
     * User Login service with password check and username check.
     *
     * @return ture iff login is successful, otherwise, false.
     */
    public Boolean userLogin() {
        return userDao.existsByUsernameAndEncryptedPassword(this.userName, PasswordEncoderMD5.encode(this.password));
    }


    /**
     * Query information by username.
     *
     * @return Iterator of username, note that we are only allowing one username to appear ControlPresentInfo the database for
     * once, so the queried item should be the first one ControlPresentInfo the iterator.
     */
    private User queryByUserName(){
        return userDao.findByUsername(this.userName);
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

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}