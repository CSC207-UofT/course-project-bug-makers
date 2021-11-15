package com.courseApp.calendarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.courseApp.constants.Constants;
import com.courseApp.constants.Exceptions;
import com.courseApp.utils.AggregateList;
import com.courseApp.utils.TimeConversion;

/**
 * Subclass of CalendarFactory that generates a 7-day calendar from Sunday to Saturday.
 */
@SuppressWarnings("DuplicatedCode") // as it is not solvable
public class WeekCalendar extends CalendarFactory implements UsePresentable {

    public WeekCalendar(Map<String, Map<String, ArrayList<String>>> formattedSchedule) {
        super(formattedSchedule);
    }

    /**
     * Convert the String weekday to an integer format indicating its location in WeekCalendar.
     *
     * @param weekday String of Days of week
     * @return integer indicating weekday's location in the WeekCalendar
     */
    protected int getWeekDay(String weekday) {
        switch (weekday) {
            case Constants.MONDAY:
                return Constants.LOCATION_FIRST_DAY + Constants.STRING_GAP;
            case Constants.TUESDAY:
                return Constants.LOCATION_TU + Constants.STRING_GAP;
            case Constants.WEDNESDAY:
                return Constants.LOCATION_WE + Constants.STRING_GAP;
            case Constants.THURSDAY:
                return Constants.LOCATION_TH + Constants.STRING_GAP;
            case Constants.FRIDAY:
                return Constants.LOCATION_FR + Constants.STRING_GAP;
        }
        throw new IllegalStateException(Exceptions.WRONG_WEEKDAY_TYPE);
    }

    /**
     * Reformat the list by setting up the size and name of the cell based on information provided
     *
     * @param desiredList the Calendar list
     * @param sections the map representing the section's date(s) and time slots(s).
     * @param weekday day in the week
     * @param courseCode the 7-character String representing a course
     */
    @Override
    protected void setupCell(List<String> desiredList, Map<String, ArrayList<String>> sections,
                             String weekday, String courseCode){
        ArrayList<String> time = sections.get(weekday);
        int startingT = TimeConversion.getIntTime(time.get(0));
        int endingT = TimeConversion.getIntTime(time.get(1));
        int weekDay = getWeekDay(weekday);
        markCell(desiredList, weekDay, startingT, courseCode);
        resizeCell(desiredList, weekDay, startingT, TimeConversion.getTimeDiff(startingT, endingT));
    }



    /**
     * This present method realizes the String representation of the WeekCalendar.
     *
     * @return a String representation of WorkdayCalendar
     */
    @Override
    public String present() {
        List<String> list = Constants.getListedWeekday();
        //loop over all the keys(course codes) ControlPresentInfo this schedule
        for(String courseCode : this.formattedSchedule.keySet()){
            Map<String, ArrayList<String>> sections = this.formattedSchedule.get(courseCode);
            //loop over the time slot(s) that this section occurs
            for (String weekday : sections.keySet()){
                setupCell(list, sections, weekday, courseCode);
            }

        }

        // return the aggregated list
        return(AggregateList.aggregate(list));
    }


}

