package com.courseApp.userService;

/**
 * Interface for realizing course addition.
 */
public interface ControlAddOne {
    /**
     * Add course to course list.
     *
     * @param username username
     * @param courseCode target course code
     * @return true iff the insertion is successful
     */
    boolean addCourse(String username, String courseCode);

    /**
     * Add course to the wish list.
     *
     * @param username username
     * @param courseCode target course code
     * @return ture iff the insertion is successful
     */
    boolean addWish(String username, String courseCode);

}
