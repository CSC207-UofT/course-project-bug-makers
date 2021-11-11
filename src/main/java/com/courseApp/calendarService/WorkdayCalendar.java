package com.courseApp.calendarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.courseApp.constants.Constants;
import com.courseApp.constants.Exceptions;
import com.courseApp.utils.AggregateList;
import com.courseApp.utils.TimeConversion;

/**
 * Subclass of CalendarFactory that generates a 5-day calendar from Monday to Friday.
 */
public class WorkdayCalendar extends CalendarFactory implements UsePresentable {

    public WorkdayCalendar(Map<String, Map<String, ArrayList<String>>> formattedSchedule) {
        super(formattedSchedule);
    }

    /**
     * Convert the String weekday to an integer format indicating its location in WorkdayCalendar.
     *
     * @param weekday String of Days of week
     * @return integer indicating weekday's location in the WorkdayCalendar
     */
    protected int getWeekDay(String weekday) {
        switch (weekday) {
            case Constants.MONDAY:
                return Constants.LOCATION_MO;
            case Constants.TUESDAY:
                return Constants.LOCATION_TU;
            case Constants.WEDNESDAY:
                return Constants.LOCATION_WE;
            case Constants.THURSDAY:
                return Constants.LOCATION_TH;
            case Constants.FRIDAY:
                return Constants.LOCATION_FR;
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
     * This present method realizes the String representation of the WorkdayCalendar.
     *
     * Generate a String representation of the desired Calendar.
     * The String representation may vary based on the calendar type.
     *
     * @return a String representation of calendar
     */
    @Override
    public String present() {
        List<String> list = Constants.getListedWorkday();
        //loop over all the keys(course codes) ControlPresentInfo this schedule
        for(String courseCode : this.formattedSchedule.keySet()){
            Map<String, ArrayList<String>> sections = this.formattedSchedule.get(courseCode);
            //loop over the time slot(s) that this section occurs
            for (String weekday : sections.keySet()){
                setupCell(list, sections, weekday, courseCode);
            }
        }
        return(AggregateList.aggregate(list));
    }



//    public static void main(String[] args) {
//        ArrayList<String> th_schedule = new ArrayList<>();
//        th_schedule.add("17:00");
//        th_schedule.add("19:00");
//
//
//        ArrayList<String> tu_schedule = new ArrayList<>();
//        tu_schedule.add("11:00");
//        tu_schedule.add("14:00");
//
//
//        ArrayList<String> th_schedule2 = new ArrayList<>();
//        th_schedule2.add("8:00");
//        th_schedule2.add("9:00");
//
//
//        ArrayList<String> mo_schedule2 = new ArrayList<>();
//        mo_schedule2.add("10:00");
//        mo_schedule2.add("11:00");
//
//        Map<String, ArrayList<String>> day = new HashMap<>();
//        day.put("TH", th_schedule);
//        day.put("TU", tu_schedule);
//
//        Map<String, ArrayList<String>> day2 = new HashMap<>();
//        day2.put("TH", th_schedule2);
//        day2.put("MO", mo_schedule2);
//
//        Map<String, Map<String, ArrayList<String>>> cad = new HashMap<>();
//        cad.put("CSC207SLEC0101", day);
//        cad.put("CSC108SLEC0102", day2);
//
//        System.out.println(cad);
//
//        System.out.println(new WeekCalendar(cad).present());
//
//    }



}

