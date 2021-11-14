package com.courseApp.dao;

import com.courseApp.entity.Course;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Interface for providing interaction with API.
 */
public interface CourseDAO {

     /**
      * Generate a course entity.
      * @return Course (an entity)
      */
     Course generateCourseEntity();


     /**
      * Return URL for querying the course information.
      * courseCode must end with the term indicator, i.e., F/S/Y,
      * otherwise throw WRONG_COURSE_CODE_FORMAT exception.
      */
     void generateQuery() throws Throwable;


     /**
      * Setter method for converting Json response from the API to a map instance variable.
      */
     void setMap() throws Throwable;


     /**
      * Getter method for getting map instance variable, where map is the Json response from the API.
      *
      * @return map type of json file of the API response
      */
     Map<String, Object> getMap();


     /**
      * Query method for getting the course pre-req.
      *
      * @return Course pre-req string
      */
     String queryCoursePrerequisite();

     /**
      * Query method for getting the course description
      *
      * @return Course Term
      */
     String queryCourseDescription();

     /**
      * Query method for getting the course Title
      *
      * @return Course Title
      */
     String queryCourseTitle();

     /**
      * Query method for getting the course sections, including TUT LEC and PRA sections.
      *
      * @return ArrayList of all sections.
      */
     List<String> queryCourseSectionList();

     /** Query method, returns the schedule for each section.
      *
      * Structure should be like: Map(section -> Map(weekday -> ArrayList(timing)))
      *
      *
      * @return schedule for each section.
      */
     Map<String, Map<String, ArrayList<String>>> queryCourseSectionScheduleMap();

     /**
      * Query the instructor name for each section.
      *
      * @return Map of section as key and Instructor name as value.
      */
     Map<String, String> querySectionInstructorMap();

}
