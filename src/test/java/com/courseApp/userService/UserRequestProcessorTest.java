package com.courseApp.userService;

import com.courseApp.courseService.ScheduleUpdater;
import com.courseApp.entity.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class UserRequestProcessorTest {
    UserRequestProcessor urp;

    @BeforeEach
    void setUp() {
        urp = new UserRequestProcessor("Turp", "1111");
        urp.insertOneCourse("CSC209SLEC0102");
        urp.insertOneWish("BIO260SLEC0101");
    }

    @AfterEach
    void tearDown() {
        urp.clearCourseList();
        urp.clearScheduleList();
        urp.clearWishList();
    }

    @Test
    void userLogin() {
        assertTrue(urp.userLogin());
    }

    @Test
    void userRegister() {
        Random random = new Random();
        String username = valueOf(random.ints(10000,20000));
        UserRequestProcessor register = new UserRequestProcessor(username, "1111");
        assertTrue(register.userRegister());
    }

    @Test
    void queryUserCourseList() {
        assertEquals(urp.queryUserCourseList().toString(), "[CSC209SLEC0102]");
    }

    @Test
    void queryUserWishList() {
        assertEquals(urp.queryUserWishList().toString(), "[BIO260SLEC0101]");
    }

    @Test
    void queryUserScheduleList() {
        assertEquals(urp.queryUserScheduleList().toString(), "[]");
    }

    @Test
    void queryUserReviewList() {
        //TODO
    }

    @Test
    void insertOneCourse() {
        assertTrue(urp.insertOneCourse("MAT223SLEC0301"));
    }

    @Test
    void insertOneWish() {
        assertTrue(urp.insertOneWish("MAT137YTUT0503"));
    }

    @Test
    void insertOneReview() {
        //TODO
    }

    @Test
    void removeOneCourse() {
        assertTrue(urp.removeOneCourse("CSC209SLEC0102"));
    }

    @Test
    void removeOneReview() {
        //TODO
    }

    @Test
    void removeOneWish() {
        assertTrue(urp.removeOneWish("BIO260SLEC0101"));
    }

    @Test
    void clearCourseList() {
        assertTrue(urp.clearCourseList());
    }

    @Test
    void clearWishList() {
        assertTrue(urp.clearWishList());
    }

    @Test
    void clearScheduleList() {
        assertTrue(urp.clearScheduleList());
    }

    @Test
    void insertOneSchedule() {
        ArrayList<String> sectionList = new ArrayList<>();
        sectionList.add("BIO260SLEC0101");
        Schedule schedule = new Schedule(sectionList);
        new ScheduleUpdater().updateScheduleMap(schedule);
        assertTrue(urp.insertOneSchedule(schedule));
    }
}