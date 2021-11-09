package com.courseApp.userService;

public interface ControlRM {
    /**
     * Remove the given course in the user's course list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @param courseCode target course code
     * @return true iff removal is successful
     */
    boolean rmCourse(String username, String courseCode);

    /**
     * Remove the given course in the user's wish list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @param courseCode target course code
     * @return true iff removal is successful
     */
    boolean rmWish(String username, String courseCode);
}
