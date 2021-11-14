package com.courseApp.calendarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.courseApp.constants.Constants;
import com.courseApp.utils.TimeConversion;

/**
 * Abstract subclass of CalendarFactory for producing a one-day calendar.
 * Its 5 subclasses each generate a calendar for one specific workday.
 * Note that itself is abstract thus it won't generate anything.
 */
public abstract class SingledayCalendar extends CalendarFactory implements UsePresentable {
    public SingledayCalendar(Map<String, Map<String, ArrayList<String>>> formattedSchedule) {
        super(formattedSchedule);
    }

    /**
     * Reformat the list by marking column that is occupied by day in the week.
     *
     * @param list the Calendar list
     * @param calendarType type of the Calendar required
     */
    protected void markSingleDay(List<String> list, String calendarType){
        list.set(Constants.FIRST_DAY_POSITION, list.get(Constants.FIRST_DAY_POSITION).substring(0,
                Constants.CELL_FILLING+Constants.COURSE_CODE_FLAG)+
                calendarType.substring(0, Constants.COURSE_WEEKDAY_FLAG) +
                list.get(Constants.FIRST_DAY_POSITION).
                        substring(Constants.CELL_FILLING+Constants.COURSE_CODE_FLAG+Constants.COURSE_WEEKDAY_FLAG));

    }
    /**
     * Convert the String weekday to an integer format indicating its location in SingledayCalendar.
     *
     * @param weekday String of Days of week
     * @return weekday's location in the SingledayCalendar
     */
    protected int getWeekDay(String weekday){
//        if (weekday.equals(Constants.MONDAY) | weekday.equals(Constants.TUESDAY)| weekday.equals(Constants.WEDNESDAY)|
//                weekday.equals(Constants.THURSDAY)| weekday.equals(Constants.FRIDAY)) {
            return Constants.LOCATION_FIRST_DAY;
    }

    /**
     * Reformat the list by setting up the size and name of the cell based on information provide
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
        int weekDay;
        weekDay = getWeekDay(weekday);
        markCell(desiredList, weekDay, startingT, courseCode);
        resizeCell(desiredList, weekDay, startingT, TimeConversion.getTimeDiff(startingT, endingT));}


}
