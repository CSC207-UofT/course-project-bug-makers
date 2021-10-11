package com.courseApp.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Course {
    private final String courseCode;
    private final String courseTitle;
    private final String courseDescription;
    private final List<String> courseSectionList;
    private final Map<String,Map<String, ArrayList<String>>> courseSectionScheduleMap;
    private final String courseTerm;

    public Course(String courseCode,
                  String courseTitle,
                  String courseDescription,
                  List<String> courseSectionList,
                  Map<String,Map<String, ArrayList<String>>>
                          courseSectionScheduleMap,
                  String courseTerm) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseSectionList = courseSectionList;
        this.courseSectionScheduleMap = courseSectionScheduleMap;
        this.courseTerm = courseTerm;
    }


    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public List<String> getCourseSectionList() {
        return courseSectionList;
    }

    public Map<String,Map<String, ArrayList<String>>> getCourseSectionScheduleMap() {
        return courseSectionScheduleMap;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

}
