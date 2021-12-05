package com.courseApp.courseService;

import com.courseApp.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseServiceControllerTest {
    CourseServiceController csc;
    Schedule schedule;
    ArrayList<String> courseList;


    @BeforeEach
    void setUp() {
        csc = new CourseServiceController();

        //// Test Schedule
        courseList = new ArrayList<>();
        courseList.add("CSC207FLEC0101");
        schedule = new Schedule(courseList);
        new ScheduleUpdater().updateScheduleMap(schedule);
    }

    @Test
    void getCourseGeneralInformation() throws Throwable {
        assertEquals("          CSC207F\n" +
                        "          - Title: Software Design\n" +
                        "          - Description: An introduction to software design and development concepts, methods, and tools using a statically-typed object-oriented programming language such as Java. Topics from: version control, unit testing, refactoring, object-oriented design and development, design patterns, advanced IDE usage, regular expressions, and reflection. Representation of floating-point numbers and introduction to numerical computation.\n" +
                        "          - Prerequisite: 60% or higher in CSC148H1/ 60% or higher in CSC111H1\n" +
                        "          - Section:\n" +
                        "                    LEC5101          {TU=[18:00, 20:00]}          L. Shorser\n" +
                        "                    LEC5201          {TH=[18:00, 20:00]}          L. Shorser\n" +
                        "                    LEC0501          {TU=[13:00, 14:00], TH=[13:00, 14:00]}          P. Gries\n" +
                        "                    LEC0301          {TU=[15:00, 16:00], TH=[15:00, 16:00]}          J. Calver\n" +
                        "                    LEC0401          {TU=[16:00, 17:00], TH=[16:00, 17:00]}          J. Calver\n" +
                        "                    LEC0101          {TU=[13:00, 14:00], TH=[13:00, 14:00]}          P. Gries\n" +
                        "                    LEC0201          {TU=[14:00, 15:00], TH=[14:00, 15:00]}          P. Gries\n" +
                        "                    TUT0501          {TH=[09:00, 11:00]}          -\n" +
                        "                    TUT0401          {MO=[16:00, 18:00]}          -\n" +
                        "                    TUT0101          {MO=[10:00, 12:00]}          -\n" +
                        "                    TUT0301          {MO=[14:00, 16:00]}          -\n" +
                        "                    TUT0201          {MO=[12:00, 14:00]}          -\n" +
                        "                    TUT5101          {MO=[18:00, 20:00]}          -\n" +
                        " ---------- ---------- ---------- ---------- ---------- ----------",
                csc.getCourseGeneralInformation("CSC207F"));
    }

    @Test
    void getSectionInformation() throws Throwable {
        assertEquals("CSC207FLEC0101\n" +
                        "          {TU=[13:00, 14:00], TH=[13:00, 14:00]}          P. Gries\n" +
                        " ---------- ---------- ---------- ---------- ---------- ----------",
                csc.getSectionInformation("CSC207FLEC0101")
                );
    }

    @Test
    void getScheduleSummary() {
        assertEquals("[CSC207FLEC0101]\n" +
                "CSC207FLEC0101\n" +
                "          {TU=[13:00, 14:00], TH=[13:00, 14:00]}\n" +
                " ---------- ---------- ---------- ---------- ---------- ----------\n",
                csc.getScheduleSummary(schedule));
    }

    @Test
    void planCourse() throws Throwable {
        //note that the runtime is long
        assertEquals("[CSC207FLEC0101, CSC207FTUT0101, CSC236FLEC0201, ECO200YLEC0301, CSC209SLEC0101, CSC209STUT0101]\n" +
                        "CSC209SLEC0101\n" +
                        "          {TU=[14:00, 15:00], TH=[14:00, 15:00]}\n" +
                        "CSC207FLEC0101\n" +
                        "          {TU=[13:00, 14:00], TH=[13:00, 14:00]}\n" +
                        "ECO200YLEC0301\n" +
                        "          {MO=[15:00, 17:00], WE=[09:00, 11:00]}\n" +
                        "CSC236FLEC0201\n" +
                        "          {MO=[12:00, 13:00], FR=[12:00, 13:00], WE=[12:00, 13:00]}\n" +
                        "CSC209STUT0101\n" +
                        "          {FR=[13:00, 14:00]}\n" +
                        "CSC207FTUT0101\n" +
                        "          {MO=[10:00, 12:00]}\n" +
                        " ---------- ---------- ---------- ---------- ---------- ----------\n",
                csc.planCourse("CoursePlanTest", 0));
    }
}