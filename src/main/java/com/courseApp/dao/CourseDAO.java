package com.courseApp.dao;

import com.courseApp.entity.Course;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Interface for providing interaction with API.
 */
public interface CourseDAO {


     Course generateCourseEntity();

     void generateQuery() throws Throwable;

     void setMap() throws Throwable;

     Map<String, Object> getMap();

     String queryCourseTerm();

     String queryCoursePrerequisite();

     String queryCourseDescription();

     String queryCourseTitle();

     List<String> queryCourseSectionList();

     Map<String, Map<String, ArrayList<String>>> queryCourseSectionScheduleMap();
}
