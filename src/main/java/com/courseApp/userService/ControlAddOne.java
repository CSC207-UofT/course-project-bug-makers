package com.courseApp.userService;

/**
 * Interface for realizing course addition.
 */
public interface ControlAddOne {
    boolean addCourse(String username, String courseCode);

    boolean addWish(String username, String courseCode);

}
