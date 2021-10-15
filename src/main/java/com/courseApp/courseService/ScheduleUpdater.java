package com.courseApp.courseService;

import com.courseApp.entity.Schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Schedule updater use case for generating the schedule map by given section list ControlPresentInfo the schedule entity.
 *
 * Note that this is handy when querying user schedule list ControlPresentInfo the DB, DB does not store schedule map information.
 */
public class ScheduleUpdater implements UseScheduleInfo {


    /**
     * Update schedule map by given Schedule entity based on schedule entity's section list.
     *
     * @param schedule updated Schedule entity.
     */
    @Override
    public void updateScheduleMap(Schedule schedule) {
        if(schedule.getScheduleMap() == null){
            Map<String, Map<String, ArrayList<String>>> res = new HashMap<>();
            for(String string: schedule.getSectionList()){
                try {
                    CourseInformationGenerator cig = new CourseInformationGenerator(string);
                    res.put(string, cig.getSectionSpecificSchedule());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            schedule.setScheduleMap(res);
        }
    }
}
