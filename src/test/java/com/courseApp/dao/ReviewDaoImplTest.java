package com.courseApp.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ALL")
class ReviewDaoImplTest {
    ReviewDaoImpl rdi =  new ReviewDaoImpl();

    @Test
    void queryCourseReview() {
        assertNull(rdi.queryCourseReview("CSC1000"));
    }

    @Test
    void queryExistingCourse() {
        assertNotNull(rdi.queryExistingCourse());
    }

}