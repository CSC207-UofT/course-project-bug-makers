package com.courseApp.calendarService;

import com.courseApp.constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.courseApp.constants.Constants;

public abstract class CalendarFactory {
    protected Map<String, Map<String, ArrayList<String>>> formattedSchedule;
    public CalendarFactory(Map<String, Map<String, ArrayList<String>>> formattedSchedule) {
        this.formattedSchedule = formattedSchedule;
    }

}
