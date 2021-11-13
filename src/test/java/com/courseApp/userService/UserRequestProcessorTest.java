package com.courseApp.userService;

import com.courseApp.courseService.ScheduleUpdater;
import com.courseApp.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRequestProcessorTest {

    @Autowired
    UserRequestProcessor urp;

    @BeforeEach
    void setUp() {
        urp.initWithUsernamePassword("t1", "t1");
    }

    @Test
    void userLogin() {
        assertTrue(urp.userLogin());
    }

    @Test
    void queryUserCourseList() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("CSC207F");
        expected.add("CSC209S");
        assertEquals(expected,urp.queryUserCourseList());
    }

    @Test
    void queryUserWishList() {
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(expected,urp.queryUserWishList());

    }

    @Test
    void queryUserScheduleList() {
    }

    @Test
    void queryUserReviewList() {
    }

    @Test
    void insertOneCourse() {
    }

    @Test
    void insertOneWish() {
    }

    @Test
    void insertOneReview() {
    }

    @Test
    void removeOneCourse() {
    }

    @Test
    void removeOneReview() {
    }

    @Test
    void removeOneWish() {
    }

    @Test
    void clearCourseList() {
    }

    @Test
    void clearWishList() {
    }

    @Test
    void clearScheduleList() {
    }

    @Test
    void insertOneSchedule() {
        urp.clearScheduleList();
        ArrayList<String> sectionList = new ArrayList<>();
        sectionList.add("CSC207FLEC0101");
        Schedule s1 = new Schedule(sectionList);
        ScheduleUpdater su = new ScheduleUpdater();
        su.updateScheduleMap(s1);
        urp.insertOneSchedule(s1);
    }
}