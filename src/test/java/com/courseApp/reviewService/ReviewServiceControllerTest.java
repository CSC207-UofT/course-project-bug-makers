package com.courseApp.reviewService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ALL")
class ReviewServiceControllerTest {
    ReviewServiceController rsc;
    Random random = new Random();
    String instructorName = "TestProf"+ random.nextInt(10000);

    @BeforeEach
    void setUp() {
        rsc = new ReviewServiceController();
    }

    @Test
    void getInstReviewSummary() {
        assertEquals("[instructorName          test          \n" +
                "          instGeneralRate          1.0\n" +
                "          instDifficultyRate          1.0\n" +
                " ---------- ---------- ---------- ---------- ---------- ----------\n" +
                "]", rsc.getInstReviewSummary("CSC207").toString());
    }

    @Test
    void getExistingCourseList() {
        assertNotNull(rsc.getExistingCourseList());
    }

    @Test
    void createNewCourse() {
        String courseCode = "TEST"+ (random.nextInt(10000) + 10000);
        assertTrue(rsc.createNewCourse(courseCode));
    }

    @Test
    void createNewInst() {
        String instCourseCode = "TESTprof"+ random.nextInt(10000);
        rsc.createNewCourse(instCourseCode);
        assertTrue(rsc.createNewInst(instCourseCode, instructorName));

    }

    @Test
    void getInstRank() {
        assertEquals("[profTest2:5.0, profTest:3.5625]", rsc.getInstRank("CSC211").toString());
    }

}