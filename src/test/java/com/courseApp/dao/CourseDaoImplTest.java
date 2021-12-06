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
        assertEquals("60% or higher in CSC148H1/ 60% or higher in CSC111H1", cdi.queryCoursePrerequisite());
    }

    @Test
    void queryCourseDescription() {
        assertEquals("An introduction to software design and development concepts, methods, and tools using a statically-typed object-oriented programming language such as Java. Topics from: version control, unit testing, refactoring, object-oriented design and development, design patterns, advanced IDE usage, regular expressions, and reflection. Representation of floating-point numbers and introduction to numerical computation.",
                cdi.queryCourseDescription());
    }

    @Test
    void queryCourseTitle() {
        assertEquals("Software Design", cdi.queryCourseTitle());
    }

    @Test
    void queryCourseSectionList() {
        assertEquals("[LEC-0101, LEC-0201, LEC-0301, LEC-0401, LEC-0501, LEC-5101, LEC-5201, TUT-0101, TUT-0201, TUT-0301, TUT-0401, TUT-0501, TUT-5101]",
                cdi.queryCourseSectionList().toString());
    }

    @Test
    void queryCourseSectionScheduleMap() {
        assertEquals("{LEC5101={TU=[18:00, 20:00]}, LEC5201={TH=[18:00, 20:00]}, LEC0501={TU=[13:00, 14:00], TH=[13:00, 14:00]}, LEC0301={TU=[15:00, 16:00], TH=[15:00, 16:00]}, LEC0401={TU=[16:00, 17:00], TH=[16:00, 17:00]}, LEC0101={TU=[13:00, 14:00], TH=[13:00, 14:00]}, LEC0201={TU=[14:00, 15:00], TH=[14:00, 15:00]}, TUT0501={TH=[09:00, 11:00]}, TUT0401={MO=[16:00, 18:00]}, TUT0101={MO=[10:00, 12:00]}, TUT0301={MO=[14:00, 16:00]}, TUT0201={MO=[12:00, 14:00]}, TUT5101={MO=[18:00, 20:00]}}",
                cdi.queryCourseSectionScheduleMap().toString());
    }

    @Test
    void querySectionInstructorMap() {
        assertEquals("{LEC0301=J. Calver, LEC0401=J. Calver, LEC0101=P. Gries, LEC0201=P. Gries, LEC5101=L. Shorser, LEC5201=L. Shorser, LEC0501=P. Gries}",
                cdi.querySectionInstructorMap().toString());
    }

}