package com.courseApp.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewDaoImplTest {
    ReviewDaoImpl rdi =  new ReviewDaoImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void queryCourseReview() {
        assertNull(rdi.queryCourseReview("CSC1000"));
    }

    @Test
    void queryExistingCourse() {
        assertNotNull(rdi.queryExistingCourse());
    }

    @Test
    void queryInstReview() {
    }

    @Test
    void queryUserReview() {
    }

    @Test
    void updateUserReviewList() {
    }

    @Test
    void createInstReview() {
    }

    @Test
    void createCourseReview() {
    }
}