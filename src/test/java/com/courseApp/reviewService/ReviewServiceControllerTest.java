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
        assertEquals(rsc.getInstReviewSummary("CSC211").toString(),"[instructorName          profTest          \n" +
                "          instGeneralRate          4.0\n" +
                "          instDifficultyRate          5.0\n" +
                " ---------- ---------- ---------- ---------- ---------- ----------\n]");
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
        //TODO
    }

}