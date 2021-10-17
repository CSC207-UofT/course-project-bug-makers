package com.courseApp.userService;

import com.courseApp.entity.Schedule;

/**
 * Interface for realizing user data update.
 */
public interface UseUpdateUserData {

    boolean insertOneCourse( String courseCode);

    boolean insertOneWish(String courseCode);

    boolean removeOneCourse(String courseCode);

    boolean removeOneWish(String courseCode);

    boolean clearCourseList();

    boolean clearWishList();

    boolean clearScheduleList();

    boolean insertOneSchedule(Schedule schedule);
}
