package com.courseApp.courseService;

import com.courseApp.calendarService.CalendarPresenter;
import com.courseApp.constants.Constants;
import com.courseApp.entity.Schedule;
import com.courseApp.userService.UserRequestProcessor;
import com.courseApp.userService.UserServiceController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Course Service Controller for Present course-related information and course planning.
 */
@RestController
@RequestMapping("/rest")
public class CourseServiceController implements ControlPresentInformation, ControlUserCoursePlanning {


    /**
     * Return string of course information, including title, description, prerequisite and sections.
     *
     * @param courseCode course code
     * @return course information String
     * @throws Throwable exception
     */
    @Override
    @RequestMapping(value = "/course/getCourseGeneralInformation/{courseCode}", method = GET, produces = "application/json")
    public String getCourseGeneralInformation(@PathVariable String courseCode) throws Throwable {
        return generateCourseGeneralInformationTable(courseCode);

    }

    /**
     * Return string of section schedule
     *
     * @param courseCodeWSection course code
     * @return section information String
     * @throws Throwable exception
     */
    @Override
    @RequestMapping(value = "/course/getSectionInformation/{courseCodeWSection}", method = GET, produces = "application/json")
    public String getSectionInformation(@PathVariable String courseCodeWSection) throws Throwable {
        return generateSectionInformation(courseCodeWSection);
    }

    /**
     * Return the String representation of schedule.
     *
     * @param schedule Schedule obj
     * @return String representation of schedule obj
     */
    @Override
    public String getScheduleSummary(Schedule schedule) {
        return generateScheduleSummary(schedule);
    }

    /**
     * Generate Course Information, including title, description, prerequisite and section schedule & instructor name.
     *
     * @param courseCode course code
     * @return String of course Information
     * @throws Throwable exceptions
     */
    private String generateCourseGeneralInformationTable(String courseCode) throws Throwable {
        // Generate title, description, and prerequisite.
        CourseInformationGenerator cig = new CourseInformationGenerator(courseCode);
        StringBuilder result = new StringBuilder();
        result.append(Constants.TRI_TAB).append(cig.getCourseCode()).append(Constants.CHANGE_LINE)
                .append(Constants.TRI_TAB).append(Constants.TITLE).append(cig.getCourseTitle()).append(Constants.CHANGE_LINE)
                .append(Constants.TRI_TAB).append(Constants.DESCRIPTION).append(cig.getCourseDescription()).append(Constants.CHANGE_LINE)
                .append(Constants.TRI_TAB).append(Constants.NAME_PREREQUISITE).append(cig.getCoursePrerequisite()).append(Constants.CHANGE_LINE)
                .append(Constants.TRI_TAB).append(Constants.SECTION).append(Constants.CHANGE_LINE);

        // Generate sections.
        for(Map.Entry<String, Map<String, ArrayList<String>>> entry : cig.getCourseSectionScheduleMap().entrySet()){
            result.append(Constants.TRI_TAB).append(Constants.TRI_TAB);
            result.append(entry.getKey()).append(Constants.TRI_TAB).append(entry.getValue());
            Map<String, String> tempMap = cig.getCourseSectionInstructorMap();
            result.append(Constants.TRI_TAB).append(tempMap.get(entry.getKey()) != null ? tempMap.get(entry.getKey()): Constants.SECTION_MARKER);
            result.append(Constants.CHANGE_LINE);
        }

        result.append(Constants.LONG_LINE);

        return result.toString();
    }

    /**
     * Generate Section schedule by course code with section which should include the section code and schedule.
     *
     * @param courseCodeWSection  course code with section
     * @return section schedule string information
     * @throws Throwable exceptions
     */
    private String generateSectionInformation(String courseCodeWSection) throws Throwable {
        // Generate Course code
        CourseInformationGenerator cig = new CourseInformationGenerator(courseCodeWSection);

        return courseCodeWSection + Constants.CHANGE_LINE + Constants.TRI_TAB +
                cig.getSectionSpecificSchedule() + Constants.TRI_TAB + cig.getSectionSpecificInstructor()+ Constants.CHANGE_LINE +
                Constants.LONG_LINE;
    }

    /**
     * Generate Schedule summary, which should include the course choices and schedule for each choice.
     *
     * @param schedule schedule entity.
     * @return String schedule summary
     */


    private String generateScheduleSummary(Schedule schedule){
        return schedule.toString();
    }

    /**
     * Plan the schedule for user with given username and password.
     * Course planning will base on courseList, then wishList with courseList at a higher priority
     * and wishList at a lower priority.
     *
     * Planned schedule should be added to scheduleList iff the planning is not successful.
     *
     * Return the planned schedule list String iff the planning is successful, otherwise, null.
     *
     * @param username username
     * @return schedule list String
     */

    @Override
    @RequestMapping(value = "/course/planCourse/{username}", method = GET, produces = "application/json")
    public String planCourse(@PathVariable String username) {
        Schedule schedule = new CoursePlanner(username).generateSchedule();
        new UserRequestProcessor(username).insertOneSchedule(schedule);
        return schedule.getSectionList().toString();
    }


}
