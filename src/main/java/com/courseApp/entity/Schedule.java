package com.courseApp.entity;


import com.courseApp.constants.Constants;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.Map;


/**
 * Schedule Entity for storing scheduled sections.
 */
public class Schedule {
    private ArrayList<String> sectionList;
    private Map<String, Map<String, ArrayList<String>>> scheduleMap;

    /**
     * Bson constructor for instantiating Schedule entity.
     *
     * @param sectionList Bson sectionList
     * @param scheduleMap Bson scheduleMap
     */
    @BsonCreator
    public Schedule(@BsonProperty(Constants.SECTION_LIST) ArrayList<String> sectionList,
                    @BsonProperty(Constants.SCHEDULE_MAP) Map<String, Map<String, ArrayList<String>>> scheduleMap) {
        this.sectionList = sectionList;
        this.scheduleMap = scheduleMap;
    }

    public void setSectionList(ArrayList<String> sectionList) {
        this.sectionList = sectionList;
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
        if(this.scheduleMap != null){
        StringBuilder sb = new StringBuilder(this.getSectionList().toString()).append(Constants.CHANGE_LINE);
        for(var entry : this.getScheduleMap().entrySet()){
            sb.append(entry.getKey()).append(Constants.CHANGE_LINE).append(Constants.TRI_TAB);
            sb.append(entry.getValue()).append(Constants.CHANGE_LINE);
        }
        sb.append(Constants.LONG_LINE).append(Constants.CHANGE_LINE);
        return sb.toString();} else{
            return this.sectionList.toString();
        }
    }

    public void setScheduleMap(Map<String, Map<String, ArrayList<String>>> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }
}
