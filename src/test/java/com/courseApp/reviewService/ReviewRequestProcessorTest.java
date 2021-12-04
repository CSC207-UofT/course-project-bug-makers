package com.courseApp.reviewService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ALL")
class ReviewRequestProcessorTest {
    ReviewRequestProcessor rrp = new ReviewRequestProcessor();
    Random random = new Random();
    String instructorName = "TestProf"+ random.nextInt(10000);
    String CourseCode = "testReviewRequestProcessor"+ random.nextInt(10000);

    @BeforeEach
    void setUp() {
        rrp = new ReviewRequestProcessor();
    }


    @Test
    void queryCourseReviewSummary() {
        assertEquals(rrp.queryCourseReviewSummary("CSC207").toString(),"{courseGeneralRate=1.0, courseDifficultyRate=1.0, courseCode=CSC207}");
    }

    @Test
    void queryInstReviewSummary() {
        assertEquals(rrp.queryInstReviewSummary("CSC211","profTest2").toString(),"{instructorName=profTest2, instGeneralRate=5.0, instDifficultyRate=2.0}");
    }

    @Test
    void queryExistingCourse() {
        assertNotNull(rrp.queryExistingCourse());
    }

    @Test
    void queryExistingInst() {
        assertEquals(rrp.queryExistingInst("CSC211").toString(), "[profTest2, profTest]");
    }

    @Test
    void insertOneUserReview() {
        rrp.createOneCourseReview(CourseCode);
        rrp.createOneInstReview(CourseCode, instructorName);
        assertTrue(rrp.insertOneUserReview(CourseCode, instructorName, "Turp", 4.0, 5.0, 3.0, "Great course, though it is kind of difficult"));
        rrp.deleteOneUserReview(CourseCode, instructorName, "Turp"); //tear down
                    }

    @Test
    void createOneInstReview() {
        String instCourseCode = "TEST"+ random.nextInt(10000);
        rrp.createOneCourseReview(instCourseCode);
        assertTrue(rrp.createOneInstReview(instCourseCode, instructorName));
    }

    @Test
    void createOneCourseReview() {
        String courseCode = "TEST"+ random.nextInt(10000);
        assertTrue(rrp.createOneCourseReview(courseCode));
    }

    @Test
    void deleteOneUserReview() {
        rrp.createOneCourseReview(CourseCode);
        rrp.createOneInstReview(CourseCode, instructorName);
        rrp.insertOneUserReview(CourseCode, instructorName, "Turp", 4.0, 5.0, 3.0, "Good for testing");
        assertTrue(rrp.deleteOneUserReview(CourseCode, instructorName, "Turp"));
    }

    @Test
    void getRecommendationMap() {
        assertEquals(rrp.getRecommendationMap("CSC211").toString(),"{profTest2={difficultyRate=2.0, recommendationScore=5.0, generalRate=5.0}, profTest={difficultyRate=4.916666666666667, recommendationScore=3.0833333333333335, generalRate=4.041666666666667}}");
    }
}