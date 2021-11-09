package com.courseApp.courseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Interface for providing basic course information.
 */
public interface UseCourseBasicInfo {

    /**
     * Get the course title.
     *
     * @return course title
     */
    String getCourseTitle();


    /**
     * Get the course code (i.e., department code + course code + term flag).
     *
     * @return course code
     */
    String getCourseCode();

    /**
     * Get the course description.
     *
     * @return course description
     */
    String getCourseDescription();

    /**
     * Get the course term.
     *
     * @return course term
     */
    String getCourseTerm();

    /**
     * Get the course pre-req.
     *
     * @return course pre-req
     */
    String getCoursePrerequisite();

    /**
     * Get the section list of the course.
     *
     * @return course section list
     */
    List<String> getCourseSectionList();

    /**
     * Get the schedule map for all sections.
     *
     * Note that the structure be like: Map(section -> Map(weekday -> ArrayList(timing)))
     *
     * @return map of schedule for all sections.
     */
    Map<String, Map<String, ArrayList<String>>> getCourseSectionScheduleMap();

    /**
     * Get the section type list for all section.
     *
     * @return List of section types in the order of section list.
     */
    List<String> getCourseSectionType();

    /**
     * Get the instructor name map for all the sections
     * Note that this only returns LEC.
     *
     * @return Map of section to instructor name.
     */
    Map<String, String> getCourseSectionInstructorMap();

}
