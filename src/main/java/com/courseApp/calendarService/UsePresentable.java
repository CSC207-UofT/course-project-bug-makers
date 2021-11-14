package com.courseApp.calendarService;

/**
 * Interface for drawing all kinds of calendars by CalendarFactory.
 */
public interface UsePresentable {
    /**
     * Generate a String representation of the desired Calendar.
     * The String representation may vary based on the calendar type.
     *
     * @return a String representation of calendar
     */
    String present();
}
