package com.courseApp.calendarService;

import com.courseApp.entity.Schedule;
import com.courseApp.userService.UserServiceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalendarPresenterTest {
    CalendarPresenter cad;
    Schedule slist;

    @BeforeEach
    void setUp() {
        UserServiceController USC = new UserServiceController();
        USC.userRegister("USC","1234567890");
        //USC.userLogin("USC","1234567890");
        USC.userClearCourseList("USC");
        USC.addCourse("USC", "CSC207FLEC0401");
        USC.addCourse("USC", "CSC207FTUT0101");
        USC.addCourse("USC", "CSC236FLEC0301");
        USC.addCourse("USC", "BCH210FLEC9901");
        USC.addCourse("USC", "ECO200FLEC5101");
        USC.getScheduleList("USC");
        slist = USC.getLatestSchedule("USC");


    }

    @Test
    void presentCalendar() {
        //assertEquals(cad.presentCalendar("Y", "Workday", (Map<String, Map<String, ArrayList<String>>>) slist)
        //== "");
    }
