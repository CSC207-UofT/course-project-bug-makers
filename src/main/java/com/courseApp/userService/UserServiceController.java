package com.courseApp.userService;


/**
 * User Service Controller for user-related demands.
 */
public class UserServiceController implements ControlLoginRegister, ControlRM, ControlClear,
        ControlPresentInfo, ControlAddOne {

    /**
     *  User login
     *
     * @param username username
     * @param password password
     * @return true iff login is successful
     */
    @Override
    public boolean userLogin(String username, String password){
        return new UserRequestProcessor(username, password).userLogin();
    }

    /**
     * User register
     *
     * @param username username
     * @param password password
     * @return true iff register is successful
     */
    @Override
    public boolean userRegister(String username, String password) {
        return new UserRequestProcessor(username, password).userRegister();
    }

    /**
     * Clear the user's course list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @return true iff clear is successful
     */
    @Override
    public boolean userClearCourseList(String username){
        return new UserRequestProcessor(username).clearCourseList();

    }

    /**
     * Clear the user's wish list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @return true iff clear is successful
     */
    @Override
    public boolean userClearWishList(String username){
        return new UserRequestProcessor(username).clearWishList();
    }

    /**
     * Clear the user's schedule list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @return true iff clear is successful
     */
    @Override
    public boolean userClearScheduleList(String username){
        return new UserRequestProcessor(username).clearScheduleList();
    }

    /**
     * Remove the given course in the user's course list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @param courseCode target course code
     * @return true iff removal is successful
     */
    public boolean rmCourse(String username, String courseCode){
        return new UserRequestProcessor(username).removeOneCourse(courseCode);
    }

    /**
     * Remove the given course in the user's wish list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @param courseCode target course code
     * @return true iff removal is successful
     */
    @Override
    public boolean rmWish(String username, String courseCode){
        return new UserRequestProcessor(username).removeOneWish(courseCode);
    }

    /**
     * Return a String representation of user's course list.
     *
     * @param username username
     * @return String representation of user course list
     */
    @Override
    public String getCourseList(String username){
        return new UserRequestProcessor(username).queryUserCourseList().toString();
    }

    /**
     * Return a String representation of user's wish list.
     *
     * @param username username
     * @return String representation of user wish list
     */
    @Override
    public String getWishList(String username){
        return new UserRequestProcessor(username).queryUserWishList().toString();
    }

    /**
     * Return a String representation of user's schedule list.
     *
     * @param username username
     * @return String representation of schedule list
     */
    @Override
    public String getScheduleList(String username){
        return new UserRequestProcessor(username).queryUserScheduleList().toString();
    }


    /**
     * Add course to course list.
     *
     * @param username username
     * @param courseCode target course code
     * @return true iff the insertion is successful
     */
    @Override
    public boolean addCourse(String username, String courseCode) {
        return new UserRequestProcessor(username).insertOneCourse(courseCode);
    }

    /**
     * Add course to the wish list.
     *
     * @param username username
     * @param courseCode target course code
     * @return ture iff the insertion is successful
     */
    @Override
    public boolean addWish(String username, String courseCode) {
        return new UserRequestProcessor(username).insertOneWish(courseCode);
    }

//    public static void main(String[] args) {
//        System.out.println(new UserServiceController().userRegister("bugMaker", "bugMaker"));
//        System.out.println(new UserServiceController().getCourseList("bugMaker"));
//        System.out.println(new UserServiceController().addCourse("bugMaker", "CSC207FLEC0101"));
//        System.out.println(new UserServiceController().getCourseList("bugMaker"));
//    }
}
