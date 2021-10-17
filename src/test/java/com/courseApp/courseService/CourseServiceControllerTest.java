package com.courseApp.courseService;

import com.courseApp.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceControllerTest {
    CourseServiceController csc;
    String SectionInformation;
    ArrayList<String> courseList;
    Schedule schedule;
    String ScheduleTest;

    @BeforeEach
    void setUp() {
        csc = new CourseServiceController();
        SectionInformation = """
                CSC207FLEC0101
                          {TU=[13:00, 14:00], TH=[13:00, 14:00]}
                 ---------- ---------- ---------- ---------- ---------- ----------""";

        ////// Test Schedule now
        courseList = new ArrayList<>();
        courseList.add("CSC207FLEC0101");
        schedule = new Schedule(courseList);
        new ScheduleUpdater().updateScheduleMap(schedule);

        ScheduleTest = """
                [CSC207FLEC0101]
                CSC207FLEC0101
                          {TU=[13:00, 14:00], TH=[13:00, 14:00]}
                 ---------- ---------- ---------- ---------- ---------- ----------
                 """;


    }

    @Test
    void getSectionInformation() throws Throwable {
        assertEquals(csc.getSectionInformation("CSC207FLEC0101"), SectionInformation);
    }


    @Test
    void getScheduleSummary() {
        assertEquals(csc.getScheduleSummary(schedule), ScheduleTest);
    }
}