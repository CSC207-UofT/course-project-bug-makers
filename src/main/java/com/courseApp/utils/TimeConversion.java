package com.courseApp.utils;

import com.courseApp.constants.Constants;


/**
 * Helper class for converting different types of time input to a readable integer format for CalendarFactory.
 */
public class TimeConversion {
    /**
     * Convert the String timeString to its integer format.
     *
     * @param timeString String of time
     * @return time ControlPresentInfo integer form
     */
    public static int getIntTime(String timeString){
        return Integer.parseInt(timeString.split(Constants.TIME_COMMA)[0]);
    }

    /**
     * Generate the duration of the event.
     *
     * @param starting Starting time of this section
     * @param ending Ending time of this section
     * @return the duration of this event ControlPresentInfo hour.
     */
    public static int getTimeDiff(int starting, int ending){
        return (ending - starting);
    }
}
