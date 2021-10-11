package com.courseApp.DAO;

import com.courseApp.entity.Course;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CourseDAO {

     Course generateCourseEntity();

     void generateQuery() throws Throwable;

     void setMap() throws Throwable;

     Map<String, Object> getMap();

     String queryCourseTerm();

     String queryCourseDescription();

     String queryCourseTitle();

     List<String> queryCourseSectionList();

     Map<String, Map<String, ArrayList<String>>> queryCourseSectionScheduleMap();
}
