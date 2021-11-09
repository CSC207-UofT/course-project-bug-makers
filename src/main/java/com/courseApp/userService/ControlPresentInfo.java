package com.courseApp.userService;

public interface ControlPresentInfo {

    /**
     * Return a String representation of user's course list.
     *
     * @param username username
     * @return String representation of user course list
     */
    String getCourseList(String username);

    /**
     * Return a String representation of user's wish list.
     *
     * @param username username
     * @return String representation of user wish list
     */
    String getWishList(String username);

    /**
     * Return a String representation of user's schedule list.
     *
     * @param username username
     * @return String representation of schedule list
     */
    String getScheduleList(String username);
}
