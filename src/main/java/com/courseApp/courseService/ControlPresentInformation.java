package com.courseApp.courseService;

import com.courseApp.entity.Schedule;

/**
 * Interface for providing various of vernal course-related information,
 * including specific course information, section information and schedule summary.
 */
public interface ControlPresentInformation {

    /**
     * Return string of course information, including title, description, prerequisite and sections.
     *
     * @param courseCode course code
     * @return course information String
     * @throws Throwable exception
     */
    String getCourseGeneralInformation(String courseCode) throws Throwable;

    /**
     * Return string of section schedule
     *
     * @param courseCodeWSection course code
     * @return section information String
     * @throws Throwable exception
     */
    String getSectionInformation(String courseCodeWSection) throws Throwable;

    /**
     * Return the String representation of schedule.
     *
     * @param schedule Schedule obj
     * @return String representation of schedule obj
     */
    String getScheduleSummary(Schedule schedule);

}
