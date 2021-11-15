package com.courseApp.courseService;

import com.courseApp.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;


class CoursePlannerTest {
    CoursePlanner csc;
    Schedule schedule;
    ArrayList<String> courseList;


    @BeforeEach
    void setUp() throws Throwable {
        csc = new CoursePlanner("CalendarTest2");

        //// Test Schedule
        courseList = new ArrayList<>();
        courseList.add("CSC207FLEC0101");
        schedule = new Schedule(courseList);
        new ScheduleUpdater().updateScheduleMap(schedule);
    }

}