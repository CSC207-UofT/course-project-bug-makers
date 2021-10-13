package com.courseApp.DAO;

import com.courseApp.entity.Schedule;
import com.courseApp.entity.User;

import java.util.ArrayList;

/**
 * UserDAO interface for querying user data, register action and login action.
 */
public interface UserDAO {

    User queryUser();

    Boolean queryUserInDB();

    Boolean userRegister();

    Boolean userLogin();

    String queryUserRole();

    ArrayList<String> queryCourseList();

    ArrayList<String> queryWishList();

    ArrayList<Schedule> queryScheduleList();

}
