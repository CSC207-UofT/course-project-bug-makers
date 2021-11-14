package com.courseApp.userService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceControllerTest {
    UserServiceController usc;
    Random random;
    String username;

    @BeforeEach
    void setUp() {
        usc = new UserServiceController();
        random = new Random();
        username = valueOf(random.nextInt(10000));
    }

    @Test
    void userLogin() {
        assertTrue(usc.userLogin("tUSC", "0000"));
    }

    @Test
    void userRegister() {
        assertTrue(usc.userRegister(username, "0000"));
    }

    @Test
    void addCourse() {
        assertTrue(usc.addCourse("tUSC", "BIO260SLEC0101"));
    }

    @Test
    void addWish() {
        assertTrue(usc.addWish("tUSC", "CSC236FLEC0301"));
    }

    @Test
    void userClearCourseList() {
        assertTrue(usc.userClearCourseList("tUSC"));
    }

    @Test
    void userClearWishList() {
        assertTrue(usc.userClearWishList("tUSC"));
    }

    @Test
    void userClearScheduleList() {
        assertTrue(usc.userClearScheduleList("tUSC"));
    }

    @Test
    void rmCourse() {
        usc.addCourse("tUSC", "BIO260SLEC0101");
        assertTrue(usc.rmCourse("tUSC", "BIO260SLEC0101"));
    }

    @Test
    void rmWish() {
        usc.addWish("tUSC", "CSC236FLEC0301");
        assertTrue(usc.rmWish("tUSC", "CSC236FLEC0301"));
    }

    @Test
    void getCourseList() {
        assertEquals(usc.getCourseList("CanlendarTest1"), "[BIO230FLEC9901, MAT223SLEC0301, CSC209SLEC0102, MAT137YTUT0503]");
    }

    @Test
    void getWishList() {
        assertEquals(usc.getWishList("CanlendarTest1"), "[]");
    }

    @Test
    void getScheduleList() {
        assertEquals(usc.getWishList("CalendarTest2"), "[]");
    }


    @Test
    void getLatestSchedule() {
        assertEquals(usc.getLatestSchedule("CalendarTest2").toString(), "[CSC207FTUT0101, CSC236FLEC0301, CSC207FLEC0401, ECO200YLEC5101, CSC209SLEC0102, BIO260SLEC0101]\n" +
                "CSC209SLEC0102\n" +
                "          {TU=[14:00, 15:00], TH=[14:00, 15:00]}\n" +
                "CSC207FLEC0401\n" +
                "          {TU=[16:00, 17:00], TH=[16:00, 17:00]}\n" +
                "BIO260SLEC0101\n" +
                "          {MO=[10:00, 11:00], FR=[10:00, 11:00], WE=[10:00, 11:00]}\n" +
                "CSC236FLEC0301\n" +
                "          {MO=[13:00, 14:00], FR=[13:00, 14:00], WE=[13:00, 14:00]}\n" +
                "CSC207FTUT0101\n" +
                "          {MO=[10:00, 12:00]}\n" +
                "ECO200YLEC5101\n" +
                "          {TU=[18:00, 20:00], WE=[18:00, 20:00]}\n" +
                " ---------- ---------- ---------- ---------- ---------- ----------\n");
    }

    @AfterEach
    void tearDown() {
        usc.userClearCourseList("tUSC");
        usc.userClearWishList("tUSC");
        usc.userClearScheduleList("tUSC");
    }
}