package com.courseApp.entity;


import com.courseApp.constants.Constants;

import java.util.ArrayList;
import java.util.Map;


/**
 * Schedule Entity for storing scheduled sections.
 */
public class Schedule {
    private final ArrayList<String> sectionList;
    private Map<String, Map<String, ArrayList<String>>> scheduleMap;

    public Schedule(ArrayList<String> sectionList, Map<String, Map<String, ArrayList<String>>> scheduleMap) {
        this.sectionList = sectionList;
        this.scheduleMap = scheduleMap;
    }

    public Schedule(Map<String, Map<String, ArrayList<String>>> scheduleMap) {
        this.sectionList = new ArrayList<>(scheduleMap.keySet());
        this.scheduleMap = scheduleMap;
    }

    public Schedule(ArrayList<String> sectionList){
        this.sectionList = sectionList;
    }

    /**
     * Getting section list.
     *
     * @return array list of course code with sections
     */
    public ArrayList<String> getSectionList() {
        return sectionList;
    }

    /**
     * Getting schedule map.
     *
     * The map must be ControlPresentInfo format: Map(courseCodeW/Section -> Map(weekDay -> ArrayList(schedule)))
     *
     * @return map of schedules
     */
    public Map<String, Map<String, ArrayList<String>>> getScheduleMap() {
        return scheduleMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getSectionList().toString()).append(Constants.CHANGE_LINE);
        for(var entry : this.getScheduleMap().entrySet()){
            sb.append(entry.getKey()).append(Constants.CHANGE_LINE).append(Constants.TRI_TAB);
            sb.append(entry.getValue()).append(Constants.CHANGE_LINE);
        }
        sb.append(Constants.LONG_LINE).append(Constants.CHANGE_LINE);
        return sb.toString();
    }

    public void setScheduleMap(Map<String, Map<String, ArrayList<String>>> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }
}
