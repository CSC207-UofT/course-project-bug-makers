package com.courseApp.calendarService;

import java.util.ArrayList;
import java.util.Map;

/**
 * Display a calendar.
 */
public interface CalendarPresentation {
    String presentCalendar(String termType, String calendarType,
                           Map<String, Map<String, ArrayList<String>>> rawSchedule);
}
