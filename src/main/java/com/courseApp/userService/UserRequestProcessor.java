package com.courseApp.userService;

import com.courseApp.dao.UserDAO;
import com.courseApp.dao.UserDaoImpl;
import com.courseApp.entity.Schedule;
import com.courseApp.entity.UserReview;

import java.util.ArrayList;

/** User request processor use case, which implements query user data, user login/register and update data
 * functions.
 *
 */
public class UserRequestProcessor implements UseQueryUserData, UseLoginRegister, UseUpdateUserData {

     private final String username;
     private String password;

    /**
     * Constructor of taking ControlPresentInfo one username and password.
     *
     * @param username username
     * @param password password
     */
    public UserRequestProcessor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor of taking ControlPresentInfo only username.
     *
     * @param username username
     */
    public UserRequestProcessor(String username) {
        this.username = username;
    }

    /**
     * User Login Authentication.
     *
     * @return True iff the username and password are consistent with the data in the database.
     */
    @Override
    public boolean userLogin() {
        return new UserDaoImpl(this.username, this.password).userLogin();
    }

    /**
     * User register service. It will check if the username already exists.
     *
     * @return True iff register is successful
     */
    @Override
    public boolean userRegister(){
        return new UserDaoImpl(this.username, this.password).userRegister();
    }

    /**
     * Return course list for user. Note that method should be called after  authentication.
     *
     * @return List of Course List.
     */
    @Override
    public ArrayList<String> queryUserCourseList(){
        return new UserDaoImpl(this.username).queryCourseList();
    }


    /**
     * Return user wish list. Note that method should only be called after  authentication.
     *
     * @return List of wish list.
     */
    @Override
    public ArrayList<String> queryUserWishList(){
        return new UserDaoImpl(this.username).queryWishList();
    }


    /**
     * Return user schedule list. Note that method should only be called after  authentication.
     *
     * @return List of user schedule
     */
    @Override
    public ArrayList<Schedule> queryUserScheduleList(){
        return new UserDaoImpl(this.username).queryScheduleList();
    }

    /**
     * Return user schedule list of the targeted user. Note that this method should only be called after authentication
     *
     * @return List of User Review
     */
    @Override
    public ArrayList<UserReview> queryUserReviewList() {
        return new UserDaoImpl(this.username).queryUserReviewList();
    }


    /**
     * Insert one course to the user course list.
     * Note that method should only be called after  authentication.
     *
     * @param courseCode course code
     * @return ture iff the insertion is successful
     */
    @Override
    public boolean insertOneCourse(String courseCode) {
        UserDAO userDao = new UserDaoImpl(this.username);
        ArrayList<String> res = userDao.queryCourseList();
        res.add(courseCode);
        return userDao.updateCourseList(res);

    }

    /**
     * Insert one course to the wishList.
     * Note that method should be called after authentication.
     *
     * @param courseCode course code
     * @return true iff the insertion is successful
     */
    @Override
    public boolean insertOneWish(String courseCode) {
        UserDAO userDao = new UserDaoImpl(this.username);
        ArrayList<String> res = userDao.queryWishList();
        res.add(courseCode);
        userDao.updateWishList(res);
        return true;
    }

    /**
     * Insert one user review to the reviewList.
     * Note that method should be called after  authentication.
     *
     * @param userReview user review
     * @return true iff the insertion is successful
     */
    @Override
    public boolean insertOneReview(UserReview userReview) {
        UserDAO userDao = new UserDaoImpl(this.username);
        ArrayList<UserReview> res = userDao.queryUserReviewList();
        res.add(userReview);
        userDao.updateUserReviewList(res);
        return true;
    }

    /**
     * Remove one course ControlPresentInfo the user course list, the result is false if there is no such course ControlPresentInfo the course list.
     * Note that method should be called after authentication.
     *
     * @param courseCode course code
     * @return true iff the removal is successful.
     */
    @Override
    public boolean removeOneCourse(String courseCode) {
        UserDAO userDao = new UserDaoImpl(this.username);
        ArrayList<String> res = userDao.queryCourseList();
        if (res.contains(courseCode)){
            res.remove(courseCode);
            userDao.updateCourseList(res);
            return true;
        }
        return false;
    }

    /**
     * Remove the one course review from the review list of targeted user.
     *
     * @param userReview user review entity
     * @return true iff the removal is successful
     */
    @Override
    public boolean removeOneReview(UserReview userReview) {
        UserDAO userDao = new UserDaoImpl(this.username);
        ArrayList<UserReview> res = userDao.queryUserReviewList();
        if (res.contains(userReview)){
            res.remove(userReview);
            userDao.updateUserReviewList(res);
            return true;
        }
        return false;
    }

    /**
     * Remove one course ControlPresentInfo the wish list, the result is false if there is no such course ControlPresentInfo the wish list.
     * Note that method should be called after  authentication.
     *
     * @param courseCode course code
     * @return true iff the removal is successful.
     */
    @Override
    public boolean removeOneWish(String courseCode) {
        UserDAO userDao = new UserDaoImpl(this.username);
        ArrayList<String> res = userDao.queryWishList();
        if (res.contains(courseCode)){
            res.remove(courseCode);
            userDao.updateWishList(res);
            return true;
        }
        return false;}

    /**
     * Clear the course list.
     *
     * @return true iff the process is successful
     */
    @Override
    public boolean clearCourseList() {
        UserDAO userDao = new UserDaoImpl(this.username);
        userDao.updateCourseList(new ArrayList<>());
        return true;
    }

    /**
     * Clear the wish list.
     *
     * @return true iff the process is successful
     */
    @Override
    public boolean clearWishList() {
        UserDAO userDao = new UserDaoImpl(this.username);
        userDao.updateWishList(new ArrayList<>());
        return true;
    }

    /**
     * Clear the schedule list.
     *
     * @return true iff the process is successful
     */
    @Override
    public boolean clearScheduleList() {
        UserDAO userDao = new UserDaoImpl(this.username);
        return userDao.updateScheduleList(new ArrayList<>());

    }

    /**
     * Update the Schedule to the head of schedule list.
     *
     * @param schedule schedule
     * @return true iff the process is successful
     */
    @Override
    public boolean insertOneSchedule(Schedule schedule) {
        UserDAO userDao = new UserDaoImpl(this.username);
        ArrayList<Schedule> res = userDao.queryScheduleList();
        res.add(0, schedule);
        return userDao.updateScheduleList(res);

    }

//    public static void main(String[] args) {
//        new UserRequestProcessor().clearCourseList("TestRegister2");
//    }
}
