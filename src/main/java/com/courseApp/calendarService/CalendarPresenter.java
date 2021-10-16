package com.courseApp.calendarService;

import com.courseApp.constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Presenter for presenting a target calendar.
 */
public class CalendarPresenter implements ControlCalendarPresentation {
    /**
     * Generate the header for the calendar.
     *
     * @param termType the term(s) we want
     * @return the String header for the calendar
     */
    private String headerGenerator(String termType){
        String resultingHeader = "";
        if (termType.equals(Constants.YEAR)){
            resultingHeader += Constants.YEAR_HEADER;
        }
        if (termType.equals(Constants.FALL_TERM) || termType.equals(Constants.YEAR)){
            resultingHeader += Constants.FALL_HEADER;
        }
        else {resultingHeader += Constants.WINTER_HEADER;}
        return resultingHeader;
    }


    /**
     * Provide a processed schedule list for one term (fall or winter). If the type is "Y", provide a fall schedule list.
     *
     * @param termType the term(s) we want
     * @param rawSchedule the schedule list for the year
     * @return the schedule list of this term.
     */
    private Map<String, Map<String, ArrayList<String>>> scheduleProcessor
            (String termType, Map<String, Map<String, ArrayList<String>>> rawSchedule){
        Map<String, Map<String, ArrayList<String>>> fallSchedule =
                new HashMap<>();
        Map<String, Map<String, ArrayList<String>>> winterSchedule =
                new HashMap<>();
        // Filter target term
        for (String course: rawSchedule.keySet()){
            boolean isYear = course.substring(Constants.COURSE_CODE_FLAG - 1, Constants.COURSE_CODE_FLAG).equals(Constants.YEAR);
            if (course.substring(Constants.COURSE_CODE_FLAG -1, Constants.COURSE_CODE_FLAG).equals(Constants.FALL_TERM)||
                    isYear){
                fallSchedule.put(course, rawSchedule.get(course));}
            if (course.substring(Constants.COURSE_CODE_FLAG -1, Constants.COURSE_CODE_FLAG).equals(Constants.WINTER_TERM) ||
                    isYear){
                winterSchedule.put(course, rawSchedule.get(course));
            }
        }
        return termType.equals(Constants.WINTER_TERM) ? winterSchedule: fallSchedule;
    }

    /**
     * Generate a calendarType Calendar of the termType.
     *
     * @param termType the term(s) we want
     * @param calendarType the type of the Calendar the user want
     * @param rawSchedule the input hashmap from the schedule, containing all the courses of the year.
     * @return a presentation of WorkdayCalendar of the termType ControlPresentInfo CMD shell.
     */
    public String presentCalendar(String termType, String calendarType,
                                  Map<String, Map<String, ArrayList<String>>> rawSchedule){
        String resultingCalendar = "";
        resultingCalendar += headerGenerator(termType);
        // Check calendar type
        if (calendarType.equals(Constants.TYPE_WORKDAY)){
            WorkdayCalendar workdayCalendar = new WorkdayCalendar(scheduleProcessor(termType, rawSchedule));
            resultingCalendar += workdayCalendar.present();
            // Check term
            if (termType.equals(Constants.YEAR)){
                resultingCalendar += headerGenerator(Constants.WINTER_TERM);
                WorkdayCalendar workdayCalendarWinter =
                        new WorkdayCalendar(scheduleProcessor(Constants.WINTER_TERM, rawSchedule));
                resultingCalendar += workdayCalendarWinter.present();
            }
        }
    return resultingCalendar;
    }

//    public static void main(String[] args) {
//
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
//
//
//        Map<String, ArrayList<String>> day2 = new HashMap<>();
//        day2.put("TH", th_schedule2);
//
//        Map<String, ArrayList<String>> day3 = new HashMap<>();
//        day3.put("TU", tu_schedule);
//
//        Map<String, ArrayList<String>> day4 = new HashMap<>();
//        day4.put("MO", mo_schedule2);
//
//        Map<String, Map<String, ArrayList<String>>> cad = new HashMap<>();
//        cad.put("CSC207SLEC0101", day);
//        cad.put("CSC108SLEC0102", day2);
//        cad.put("MAT137YLEC0102", day3);
//        cad.put("CSC209FLEC0102", day4);
//
//        System.out.println(new CalendarPresenter().presentCalendar("W",
//                "Workday", cad));
//
//    }
}
