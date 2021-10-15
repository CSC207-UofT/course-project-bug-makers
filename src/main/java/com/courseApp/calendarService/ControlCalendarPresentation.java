package com.courseApp.calendarService;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface for displaying a calendar.
 */
public interface ControlCalendarPresentation {
    String presentCalendar(String termType, String calendarType,
                           Map<String, Map<String, ArrayList<String>>> rawSchedule);
}
