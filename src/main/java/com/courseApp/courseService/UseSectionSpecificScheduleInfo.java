package com.courseApp.courseService;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface for providing section specific information.
 */
public interface UseSectionSpecificScheduleInfo {

    /**
     * Get the schedule map for a single section with section by provided section code.
     *
     * @return map of schedule for all sections
     * @throws Throwable No section code exception
     */
    Map<String, ArrayList<String>> getSectionSpecificSchedule() throws Throwable;

    /**
     * Get the instructor name of the given section.
     *
     * @return name of the given section
     */
    String getSectionSpecificInstructor() throws Throwable;

}
