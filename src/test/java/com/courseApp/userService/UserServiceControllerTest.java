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
        assertEquals("[BIO230FLEC9901, MAT223SLEC0301, CSC209SLEC0102, MAT137YTUT0503]", usc.getCourseList("CanlendarTest1"));
    }

    @Test
    void getWishList() {
        assertEquals("[]", usc.getWishList("CanlendarTest1"));
    }

    @Test
    void getScheduleList() {
        assertEquals("[]", usc.getWishList("CalendarTest2"));
    }


    @Test
    void getLatestSchedule() {
        assertEquals("[CSC207FLEC0101, CSC207FTUT0101, CSC236FLEC0201, ECO200YLEC0401, CSC209SLEC0101, CSC209STUT0101, BIO260SLEC0101, BIO260STUT0101]\n" +
                        "CSC209SLEC0101\n" +
                        "          {TU=[14:00, 15:00], TH=[14:00, 15:00]}\n" +
                        "CSC207FLEC0101\n" +
                        "          {TU=[13:00, 14:00], TH=[13:00, 14:00]}\n" +
                        "ECO200YLEC0401\n" +
                        "          {TU=[15:00, 17:00], WE=[18:00, 20:00]}\n" +
                        "BIO260SLEC0101\n" +
                        "          {MO=[10:00, 11:00], FR=[10:00, 11:00], WE=[10:00, 11:00]}\n" +
                        "CSC236FLEC0201\n" +
                        "          {MO=[12:00, 13:00], FR=[12:00, 13:00], WE=[12:00, 13:00]}\n" +
                        "CSC209STUT0101\n" +
                        "          {FR=[13:00, 14:00]}\n" +
                        "CSC207FTUT0101\n" +
                        "          {MO=[10:00, 12:00]}\n" +
                        "BIO260STUT0101\n" +
                        "          {WE=[11:00, 12:00]}\n" +
                        " ---------- ---------- ---------- ---------- ---------- ----------\n",
                usc.getLatestSchedule("CalendarTest2").toString());
    }

    @AfterEach
    void tearDown() {
        usc.userClearCourseList("tUSC");
        usc.userClearWishList("tUSC");
        usc.userClearScheduleList("tUSC");
    }
}