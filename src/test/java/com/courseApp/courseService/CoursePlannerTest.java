package com.courseApp.courseService;

import com.courseApp.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CoursePlannerTest {
    CoursePlanner csc;
    Schedule schedule;
    ArrayList<String> courseList;


    @BeforeEach
    void setUp() {
        csc = new CoursePlanner("CalendarTest2");

        //// Test Schedule
        courseList = new ArrayList<>();
        courseList.add("CSC207FLEC0101");
        schedule = new Schedule(courseList);
        new ScheduleUpdater().updateScheduleMap(schedule);
    }

    @Test
    void generateSchedule() {
//        assertEquals(csc.generateSchedule().toString(), "[CSC207FTUT0101, CSC236FLEC0301, CSC207FLEC0401, ECO200YLEC5101, CSC209SLEC0102, BIO260SLEC0101]\n" +
//                "CSC209SLEC0102\n" +
//                "          {TU=[14:00, 15:00], TH=[14:00, 15:00]}\n" +
//                "CSC207FLEC0401\n" +
//                "          {TU=[16:00, 17:00], TH=[16:00, 17:00]}\n" +
//                "BIO260SLEC0101\n" +
//                "          {MO=[10:00, 11:00], FR=[10:00, 11:00], WE=[10:00, 11:00]}\n" +
//                "CSC236FLEC0301\n" +
//                "          {MO=[13:00, 14:00], FR=[13:00, 14:00], WE=[13:00, 14:00]}\n" +
//                "CSC207FTUT0101\n" +
//                "          {MO=[10:00, 12:00]}\n" +
//                "ECO200YLEC5101\n" +
//                "          {TU=[18:00, 20:00], WE=[18:00, 20:00]}\n" +
//                " ---------- ---------- ---------- ---------- ---------- ----------\n");
    }
}