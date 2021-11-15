package com.courseApp.calendarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.courseApp.constants.Constants;
import com.courseApp.utils.AggregateList;

/**
 * Subclass of SingledayCalendar, presenting only Wednesday's courses.
 */
public class WednesdayCalendar extends SingledayCalendar implements UsePresentable {
    public WednesdayCalendar(Map<String, Map<String, ArrayList<String>>> formattedSchedule) {
        super(formattedSchedule);
    }

    /**
     * This present method realizes the String representation of the calendar for Wednesday's courses.
     *
     * @return a String presentation of WednesdayCalendar.
     */
    @Override
    public String present(){
        List<String> list = Constants.getListedSingleday();
        //loop over all the keys(course codes) ControlPresentInfo this schedule
        markSingleDay(list, Constants.TYPE_WEDNESDAY);
        for(String courseCode : this.formattedSchedule.keySet()){
            Map<String, ArrayList<String>> sections = this.formattedSchedule.get(courseCode);
            //loop over the time slot(s) that this section occurs
            for (String weekday : sections.keySet()){
                if (weekday.equals(Constants.WEDNESDAY)){
                    setupCell(list, sections, weekday, courseCode);}
            }
        }
        return(AggregateList.aggregate(list));
    }
}
