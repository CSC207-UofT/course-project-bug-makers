package com.courseApp.calendarService;

import com.courseApp.constants.Constants;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.courseApp.constants.Exceptions;
import com.courseApp.courseService.CourseServiceController;
import com.courseApp.entity.Schedule;
import com.courseApp.userService.UserServiceController;

/**
 * Presenter for presenting a target calendar.
 */
public class CalendarPresenter implements ControlCalendarPresentation {
    /**
     * Generate the header for the calendar.
     *
     * @param termType the term(s) required
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
     * @param termType the term(s) required
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
            boolean isYear = course.startsWith(Constants.YEAR, Constants.COURSE_CODE_FLAG - 1);
            if (course.startsWith(Constants.FALL_TERM, Constants.COURSE_CODE_FLAG -1)||
                    isYear){
                fallSchedule.put(course, rawSchedule.get(course));}
            if (course.startsWith(Constants.WINTER_TERM, Constants.COURSE_CODE_FLAG -1) ||
                    isYear){
                winterSchedule.put(course, rawSchedule.get(course));
            }
        }
        return termType.equals(Constants.WINTER_TERM) ? winterSchedule: fallSchedule;
    }

    /**
     * Decorate the Calendar following the instruction of termType
     *
     * @param fallCalendar Fall Calendar of required type
     * @param winterCalendar Winter Calendar of required type
     * @param termType the String term(s) required
     * @param rawSchedule the input map from the schedule, containing all the courses of the year.
     * @return a String presentation of Calendar of the desired termType
     */
    private String typeDecorator(UsePresentable fallCalendar, UsePresentable winterCalendar,String termType,
                                 Map<String, Map<String, ArrayList<String>>> rawSchedule){
        String resultingCalendar = "";
        if (Objects.equals(termType, Constants.FALL_TERM)){
            resultingCalendar += fallCalendar.present();}
        else if (Objects.equals(termType,Constants.WINTER_TERM)){
            resultingCalendar += winterCalendar.present();}
        else if (Objects.equals(termType, Constants.YEAR)){
                resultingCalendar += fallCalendar.present();
                resultingCalendar += headerGenerator(Constants.WINTER_TERM);
                resultingCalendar += winterCalendar.present();
            }
        return resultingCalendar;
    }

    /**
     * Generate a String Calendar by the given term type and calendar type.
     * The String representation varies based on the calendar type.
     *
     * @param termType the term(s) required
     * @param calendarType the type of the Calendar required
     * @param rawSchedule the input map from the schedule, containing all the courses of the year.
     * @return a String presentation of a calendar for the given term type and calendar type.
     */
    public String presentCalendar(String termType, String calendarType,
                                  Map<String, Map<String, ArrayList<String>>> rawSchedule){

        // Check calendar type
        UsePresentable fallCalendar;
        UsePresentable winterCalendar;
        switch (calendarType) {
            case Constants.TYPE_WORKDAY:
                fallCalendar = new WorkdayCalendar(scheduleProcessor(Constants.FALL_TERM, rawSchedule));
                winterCalendar = new WorkdayCalendar(scheduleProcessor(Constants.WINTER_TERM, rawSchedule));
                break;
            case Constants.TYPE_MONDAY:
                fallCalendar = new MondayCalendar(scheduleProcessor(Constants.FALL_TERM, rawSchedule));
                winterCalendar = new MondayCalendar(scheduleProcessor(Constants.WINTER_TERM, rawSchedule));
                break;
            case Constants.TYPE_TUESDAY:
                fallCalendar = new TuesdayCalendar(scheduleProcessor(Constants.FALL_TERM, rawSchedule));
                winterCalendar = new TuesdayCalendar(scheduleProcessor(Constants.WINTER_TERM, rawSchedule));
                break;
            case Constants.TYPE_WEDNESDAY:
                fallCalendar = new WednesdayCalendar(scheduleProcessor(Constants.FALL_TERM, rawSchedule));
                winterCalendar = new WednesdayCalendar(scheduleProcessor(Constants.WINTER_TERM, rawSchedule));
                break;
            case Constants.TYPE_THURSDAY:
                fallCalendar = new ThursdayCalendar(scheduleProcessor(Constants.FALL_TERM, rawSchedule));
                winterCalendar = new ThursdayCalendar(scheduleProcessor(Constants.WINTER_TERM, rawSchedule));
                break;
            case Constants.TYPE_FRIDAY:
                fallCalendar = new FridayCalendar(scheduleProcessor(Constants.FALL_TERM, rawSchedule));
                winterCalendar = new FridayCalendar(scheduleProcessor(Constants.WINTER_TERM, rawSchedule));
                break;
            case Constants.TYPE_WEEK:
                fallCalendar = new WeekCalendar(scheduleProcessor(Constants.FALL_TERM, rawSchedule));
                winterCalendar = new WeekCalendar(scheduleProcessor(Constants.WINTER_TERM, rawSchedule));
                break;
            default:
                return Exceptions.WRONG_CALENDAR_TYPE;
        }
        //edit String accordingly
        String resultingCalendar = "";
        resultingCalendar += headerGenerator(termType);
        resultingCalendar += typeDecorator(fallCalendar, winterCalendar, termType, rawSchedule);
        return resultingCalendar;
    }

//    public static void main(String[] args) {
//        CalendarPresenter cad = new CalendarPresenter();
//        Map<String, Map<String, ArrayList<String>>> rawSchedule = new HashMap<>();
//        Map<String, ArrayList<String>> course = new HashMap<>();
//        ArrayList<String> array = new ArrayList<>();
//        course.put("MO", array);
//        rawSchedule.put("BIO230FLEC9901", course);
//        System.out.println(cad.presentCalendar("W", "Workday", rawSchedule));
//
//        UserServiceController USC2 = new UserServiceController();
//        USC2.userRegister("USC2", "1234567890");
//
//
////        USC2.userClearCourseList("USC2");
////        USC2.addCourse("USC2", "BIO230FLEC9901");
////        USC2.addCourse("USC2", "STA238SLEC0201");
////        USC2.addCourse("USC2", "MAT223SLEC0301");
////        USC2.addCourse("USC2", "CSC209SLEC0102");
////        USC2.addCourse("USC2", "MAT137YTUT0503");
////        USC2.getScheduleList("USC2");
////        new CourseServiceController().planCourse("USC2");
//
//
//        cad = new CalendarPresenter();
//
//    }
}