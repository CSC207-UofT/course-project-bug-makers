package com.courseApp.userService;

public interface ControlRM {
    boolean rmCourse(String username, String courseCode);

    boolean rmWish(String username, String courseCode);
}
