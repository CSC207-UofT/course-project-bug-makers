package com.courseApp.reviewService;

import com.courseApp.userService.UserRequestProcessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class ReviewRequestProcessorTest {
    ReviewRequestProcessor rrp = new ReviewRequestProcessor();
    Random random = new Random();
    String instructorName = "TestProf"+ valueOf(random.nextInt(10000));
//    rrp.createOneCourseReview("CSC209");

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
        assertEquals(rrp.queryInstReviewSummary("CSC211","profTest").toString(),"{instructorName=profTest, instGeneralRate=4.0, instDifficultyRate=5.0}");
    }

    @Test
    void queryUserReview() {
        assertEquals(rrp.queryUserReview("CSC211","profTest", "Turp").toString(),"{difficultyRate=5.0, reviewString=Great course, though it is kind of difficult, generalRate=4.0, username=Turp}");
    }

    @Test
    void queryExistingCourse() {
        assertNotNull(rrp.queryExistingCourse());
    }

    @Test
    void queryExistingInst() {
        assertEquals(rrp.queryExistingInst("CSC211").toString(), "[profTest]");
    }

    @Test
    void queryUsername() {
        assertEquals(rrp.queryUsername("CSC211", "profTest").toString(), "[Turp]");
    }

    @Test
    void insertOneUserReview() {
        assertTrue(rrp.insertOneUserReview("CSC211", "profTest", "Turp", 4.0, 5.0, 3.0, "Great course, though it is kind of difficult"));
    }

    @Test
    void createOneInstReview() {
        String instCourseCode = "TEST"+ valueOf(random.nextInt(10000));
        rrp.createOneCourseReview(instCourseCode);
        assertTrue(rrp.createOneInstReview(instCourseCode, instructorName));
    }

    @Test
    void createOneCourseReview() {
        String courseCode = "TEST"+ valueOf(random.nextInt(10000));
        assertTrue(rrp.createOneCourseReview(courseCode));
    }

    @Test
    void deleteOneUserReview() {
        rrp.insertOneUserReview("testCourse", "testCourseProf", "Turp", 3.0, 3.0, 3.0, "Good course");
        assertTrue(rrp.deleteOneUserReview("testCourse", "testCourseProf", "Turp"));
    }

    @Test
    void getRecommendationMap() {
        assertEquals(rrp.getRecommendationMap("CSC211").toString(),"{profTest={difficultyRate=5.0, recommendationScore=3.0, generalRate=4.0}}");
    }
}