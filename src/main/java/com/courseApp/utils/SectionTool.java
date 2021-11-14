package com.courseApp.utils;


import java.util.ArrayList;
import java.util.Map;

/**
 * SectionTool Entity
 */
public class SectionTool {
    private final String sectionCode;
    private final Map<String, ArrayList<String>> scheduleMap;
    private final String sectionType;
    private final String courseCode;


    public SectionTool(String sectionCode, Map<String, ArrayList<String>> scheduleMap) {
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
