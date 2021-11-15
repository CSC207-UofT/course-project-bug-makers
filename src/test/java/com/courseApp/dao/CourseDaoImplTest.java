package com.courseApp.dao;

import com.courseApp.constants.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class CourseDaoImplTest {
    CourseDaoImpl cdi;
    URL url;

    @BeforeEach
    void setUp() throws Throwable {
        cdi = new CourseDaoImpl("CSC207F");
        String res = Constants.UT_API_URL.replace(Constants.COURSE_CODE, "CSC207F".substring(0,
                "CSC207F".length() - 1));
        url = new URL(res.replace(Constants.COURSE_TERM, "CSC207F"));
    }

    @Test
    void queryCoursePrerequisite() {
        assertEquals(cdi.queryCoursePrerequisite(),"60% or higher in CSC148H1/ 60% or higher in CSC111H1");
    }

}