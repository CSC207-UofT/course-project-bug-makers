package com.courseApp.entity;


import java.util.ArrayList;
import java.util.Map;

/**
 * Section Entity
 */
public class Section {
    private final String sectionCode;
    private final Map<String, ArrayList<String>> scheduleMap;
    private final String sectionType;
    private final String courseCode;


    public Section(String sectionCode, Map<String, ArrayList<String>> scheduleMap) {
        this.sectionCode = sectionCode;
        this.scheduleMap = scheduleMap;
        this.sectionType = sectionCode.substring(7, 10);
        this.courseCode = sectionCode.substring(0, 7);
    }

    public String getSectionCode() {return sectionCode;}

    public Map<String, ArrayList<String>> getScheduleMap() {return scheduleMap;}

    public String getSectionType() {return sectionType;}

    public String getCourseCode() {return courseCode;}
}
