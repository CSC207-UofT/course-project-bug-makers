package com.courseApp.calendarService;


import com.courseApp.constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * CalendarFactory for generating calendar, which follows the factor design pattern.
 */
abstract class CalendarFactory implements UsePresentable{
    protected final Map<String, Map<String, ArrayList<String>>> formattedSchedule;
    public CalendarFactory(Map<String, Map<String, ArrayList<String>>> formattedSchedule) {
        this.formattedSchedule = formattedSchedule;
    }

    /**
     * Convert the String weekday to an integer format indicating its location in any Calendar.
     *
     * @param weekday String of Days of week
     */
    abstract int getWeekDay(String weekday);

    /**
     * Reformat the list to modify the cell size according to the length of the event.
     * Remove the dotted line between two cells if the course time exceeds 1-hour timeslot
     * 
     * @param list the Calendar list
     * @param weekDay weekday's location in the Calendar
     * @param startingT Starting time of this section
     * @param timeDifference the duration of this event in hour(s)
     */
    protected void resizeCell(List<String> list, int weekDay, int startingT, int timeDifference){
        // modify the cell if the duration is longer than an hour
        while (timeDifference > Constants.TIME_DIFFERENCE_MIN){
            int r = Constants.START_HEIGHT +
                    ((startingT + timeDifference -
                            Constants.TIME_DIFFERENCE_MIN - Constants.START_TIME)*Constants.HOUR_HEIGHT) -1;
            String rContent = list.get(r);
            list.set(r, rContent.substring(0, weekDay)+ "            " +
                    rContent.substring(weekDay + Constants.CELL_FILLING));
            timeDifference--;
        }
    }

    /**
     * Reformat the list by marking a cell that is occupied by a course in the schedule.
     *
     * @param list the Calendar list
     * @param weekDay weekday's location in the Calendar
     * @param startingT starting time of this section
     * @param courseCode course code w/ section flag, 7-char long.
     */
    protected void markCell(List<String> list, int weekDay, int startingT, String courseCode){
            int rowStarting = Constants.START_HEIGHT +
                ((startingT - Constants.START_TIME)*Constants.HOUR_HEIGHT);
        list.set(rowStarting, list.get(rowStarting).substring(0, weekDay + 3)+
                courseCode.substring(0, Constants.COURSE_CODE_LENGTH) +
                list.get(rowStarting).substring(weekDay + Constants.CELL_FILLING - 2));
        list.set(rowStarting + 1, list.get(rowStarting + 1).substring(0,weekDay + 3)+
                courseCode.substring(Constants.COURSE_CODE_LENGTH) +
                list.get(rowStarting + 1).substring(weekDay + Constants.CELL_FILLING -2));
    }

    /**
     * Reformat the list by setting up the size and name of the cell based on information provided
     *
     * @param desiredList the Calendar list
     * @param sections    the map representing the section's date(s) and time slots(s).
     * @param weekday     day in the week
     * @param courseCode  course code w/ section flag, 7-char long.
     */
    abstract void setupCell(List<String> desiredList, Map<String, ArrayList<String>> sections,
                             String weekday, String courseCode) ;


}
