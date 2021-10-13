package com.courseApp.courseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Interface for providing basic course information.
 */
public interface CourseBasicInfo {

    String getCourseTitle();
    String getCourseCode();
    String getCourseDescription();
    String getCourseTerm();
    String getCoursePrerequisite();
    List<String> getCourseSectionList();
    Map<String, Map<String, ArrayList<String>>> getCourseSectionScheduleMap();

}
