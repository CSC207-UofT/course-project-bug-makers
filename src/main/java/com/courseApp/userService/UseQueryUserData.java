package com.courseApp.userService;

import com.courseApp.entity.Schedule;

import java.util.ArrayList;

/**
 * Interface for realizing user data query.
 */
public interface UseQueryUserData {

    ArrayList<String> queryUserCourseList();

    ArrayList<String> queryUserWishList();

    ArrayList<Schedule> queryUserScheduleList();
    }


