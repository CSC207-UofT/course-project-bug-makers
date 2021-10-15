package com.courseApp.calendarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.courseApp.constants.Constants;
import com.courseApp.utils.AggregateList;
import com.courseApp.utils.TimeConversion;

public class WorkdayCalendar extends CalendarFactory implements UsePresentable {

    public WorkdayCalendar(Map<String, Map<String, ArrayList<String>>> formattedSchedule) {
        super(formattedSchedule);
    }

    /**
     * Convert the String weekday to an integer format indicating its location ControlPresentInfo WorkdayCalendar.
     *
     * @param weekday String of Days of week
     * @return weekday's location ControlPresentInfo the WorkdayCalendar
     */
    private int getWeekDay(String weekday) {
        // FEATURE SUPPORTED BY JDK16
        return switch (weekday) {
            case "MO" -> Constants.LOCATION_MO;
            case "TU" -> Constants.LOCATION_TU;
            case "WE" -> Constants.LOCATION_WE;
            case "TH" -> Constants.LOCATION_TH;
            default -> Constants.LOCATION_FR;
        };
    }

    /**
     * Modify the cell according to the length of the event.
     *
     * @param list the Calendar list
     * @param weekDay weekday's location ControlPresentInfo the WorkdayCalendar
     * @param startingT Starting time of this section
     * @param timeDifference the duration of this event ControlPresentInfo hour
     */
    private void modifyCell(List<String> list, int weekDay, int startingT, int timeDifference){
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
     * Mark cell as occupied by courseCode.
     *
     * @param list the Calendar list
     * @param weekDay weekday's location ControlPresentInfo the WorkdayCalendar
     * @param startingT Starting time of this section
     * @param courseCode The 7-character code representing a course
     */
    private void markCell(List<String> list, int weekDay, int startingT, String courseCode){
        int rowStarting = Constants.START_HEIGHT +
                ((startingT - Constants.START_TIME)*Constants.HOUR_HEIGHT);
        list.set(rowStarting, list.get(rowStarting).substring(0, weekDay + 3)+
                courseCode.substring(0, Constants.COURSE_CODE_LENGTH) +
                list.get(rowStarting).substring(weekDay + Constants.CELL_FILLING -2));
        list.set(rowStarting + 1, list.get(rowStarting + 1).substring(0,weekDay + 3)+
                courseCode.substring(Constants.COURSE_CODE_LENGTH) +
                list.get(rowStarting + 1).substring(weekDay + Constants.CELL_FILLING -2));
    }

    /**
     *
     * @return a presentation of WorkdayCalendar ControlPresentInfo CMD shell.
     */
    @Override
    public String present(){
        List<String> list = Constants.getListedWorkday();
        //loop over all the keys(course codes) ControlPresentInfo this schedule
        for(String courseCode : this.formattedSchedule.keySet()){
            Map<String, ArrayList<String>> sections = this.formattedSchedule.get(courseCode);
            //loop over the time slot(s) that this section occurs
            for (String weekday : sections.keySet()){
                ArrayList<String> time = sections.get(weekday);
                int startingT = TimeConversion.getIntTime(time.get(0));
                int endingT = TimeConversion.getIntTime(time.get(1));
                int weekDay = getWeekDay(weekday);
                markCell(list, weekDay, startingT, courseCode);
                modifyCell(list, weekDay, startingT, TimeConversion.getTimeDiff(startingT, endingT));
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
//        System.out.println(new WorkdayCalendar(cad).present());
//
//    }


}

