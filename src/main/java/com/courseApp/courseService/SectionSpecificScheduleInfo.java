package com.courseApp.courseService;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface for providing section specific information.
 */
public interface SectionSpecificScheduleInfo {

    Map<String, ArrayList<String>> getSectionSpecificSchedule() throws Throwable;

}
