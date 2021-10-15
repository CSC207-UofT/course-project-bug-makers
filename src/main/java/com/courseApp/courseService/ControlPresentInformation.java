package com.courseApp.courseService;

import com.courseApp.entity.Schedule;

/**
 * Interface for providing various of vernal course-related information,
 * including specific course information, section information and schedule summary.
 */
public interface ControlPresentInformation {

    String getCourseGeneralInformation(String courseCode) throws Throwable;

    String getSectionInformation(String courseCodeWSection) throws Throwable;

    String getScheduleSummary(Schedule schedule);

}
