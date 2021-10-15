package com.courseApp.calendarService;


import java.util.ArrayList;
import java.util.Map;


/**
 * CalendarFactory for generating calendar, which follows the factor design pattern.
 */
public abstract class CalendarFactory {
    protected final Map<String, Map<String, ArrayList<String>>> formattedSchedule;
    public CalendarFactory(Map<String, Map<String, ArrayList<String>>> formattedSchedule) {
        this.formattedSchedule = formattedSchedule;
    }

}
