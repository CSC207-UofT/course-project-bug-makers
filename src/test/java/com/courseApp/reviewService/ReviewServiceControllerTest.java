package com.courseApp.reviewService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceControllerTest {
    ReviewServiceController rsc;
    Random random = new Random();
    String instructorName = "TestProf"+ valueOf(random.nextInt(10000));

    @BeforeEach
    void setUp() {
        rsc = new ReviewServiceController();
    }

    @Test
    void getInstReviewSummary() {
        assertEquals(rsc.getInstReviewSummary("CSC211").toString(),"[instructorName          profTest          \n" +
                "          instGeneralRate          4.0\n" +
                "          instDifficultyRate          5.0\n" +
                " ---------- ---------- ---------- ---------- ---------- ----------\n]");
    }

    @Test
    void getUserReviewSummary() {
        assertEquals(rsc.getUserReviewSummary("CSC211", "profTest").toString(), "[username          Turp          \n" +
                "          generalRate          4.0\n" +
                "          difficultyRate          5.0\n" +
                " ---------- ---------- ---------- ---------- ---------- ----------\n" +
                "]");
    }

    @Test
    void getExistingCourseList() {
        assertNotNull(rsc.getExistingCourseList());
    }

    @Test
    void createNewCourse() {
        String courseCode = "TEST"+ valueOf(random.nextInt(10000)+10000);
        assertTrue(rsc.createNewCourse(courseCode));
    }

    @Test
    void createNewInst() {
        String instCourseCode = "TESTprof"+ valueOf(random.nextInt(10000));
        rsc.createNewCourse(instCourseCode);
        assertTrue(rsc.createNewInst(instCourseCode, instructorName));

    }

    @Test
    void createNewUserReviewAndDelete() throws Exception {
    // Not testable
    }

    @Test
    void editUserReview() {
        // Not testable
    }

    @Test
    void getInstRank() {
        assertEquals(rsc.getInstRank("RTC").toString(), "[{prof1=5.0, prof3=3.0, prof2=3.0, prof4=1.0}]");
    }

    @AfterEach
    void tearDown() {
    }

}