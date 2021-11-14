package com.courseApp.courseService;


import com.courseApp.constants.Constants;
import com.courseApp.constants.Exceptions;
import com.courseApp.entity.Schedule;
import com.courseApp.userService.UserRequestProcessor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Course Planner use case for implementing course planning service.
 *
 *
 *
 *
 * [UNDER CONSTRUCTION]
 */
public class CoursePlanner implements UseCoursePlanning {

    private String username;
    private ArrayList<String> courseList;
    private ArrayList<String> wishList;
    private List<String> scheduledF;
    private List<String> scheduledS;

    public CoursePlanner(String username) {
        this.username = username;
        this.courseList = new UserRequestProcessor(username).queryUserCourseList();
        this.wishList = new UserRequestProcessor(username).queryUserWishList();
        this.scheduledF = new ArrayList<>();
        this.scheduledS = new ArrayList<>();
//        this.sort2Schedule();
    }

    public Schedule generateSchedule() throws Throwable {
        UserRequestProcessor user = new UserRequestProcessor(username);
        ArrayList<ArrayList<String>> courses = ConfigureSections(user.queryUserCourseList());
        try {
            planScheduleList(new ArrayList<>(), courses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> result = planScheduleList(new ArrayList<>(), courses);
        Schedule schedule = new Schedule(result.get(0));
        new ScheduleUpdater().updateScheduleMap(schedule);
        user.insertOneSchedule(schedule);
        return schedule;

    }



    /**
     * Returns a list of valid (without time conflicts) schedules from existing schedules in schedule_list that
     * contains all sections in section_list
     *
     * @param schedule_list List of schedule entities
     * @param section_list  ArrayList of strings representing courses with section codes
     * @return ArrayList of course codes
     * @throws Throwable exceptions
     */
    private ArrayList<ArrayList<String>> planScheduleList(ArrayList<ArrayList<String>> schedule_list,
                                                         List<ArrayList<String>> section_list) throws Throwable {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (ArrayList<String> schedule : schedule_list) {
            ArrayList<String> new_schedule = new ArrayList<>(schedule);
            for (String section : section_list.get(0)) {
                for (String schedule_section : schedule) {
                    if (CheckConflict(schedule_section, section)) {
                        break;
                    } else if (schedule_section.equals(schedule.get(schedule.size() - 1))) {
                        new_schedule.add(section);
                        result.add(new_schedule);
                    }
                }
            }
        }
        if (result.isEmpty()) {
            throw new Exception(Exceptions.NO_EXISTING_SCHEDULE);
        } else if (section_list.size() == 1) {
            return result;
        } else {
            try {
                planScheduleList(result, section_list.subList(1, section_list.size() - 1));
            } catch (Exception NO_EXISTING_SCHEDULE) {
                throw new Exception(NO_EXISTING_SCHEDULE);
            }
            return planScheduleList(result, section_list.subList(1, section_list.size() - 1));
        }
    }

    /**
     * Check if two sections occur at the same time
     *
     * @param section1: A section of a course
     * @param section2: A section of a course
     * @return True iff the sections have overlapping times
     */
    private boolean CheckConflict(String section1, String section2) throws Throwable {
        if (section1.charAt(6) != section2.charAt(6)) {
            if (section1.charAt(6) != 'Y' && section2.charAt(6) != 'Y') {
                return true;
            }
        }
        try {
            new CourseInformationGenerator(section1);
            new CourseInformationGenerator(section2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        CourseInformationGenerator cig1 = new CourseInformationGenerator(section1);
        CourseInformationGenerator cig2 = new CourseInformationGenerator(section2);
        Map<String, ArrayList<String>> times1 = cig1.getSectionSpecificSchedule();
        Map<String, ArrayList<String>> times2 = cig2.getSectionSpecificSchedule();
        for (String s : times1.keySet()) {
            if (times2.containsKey(s)) {
                if (LocalTime.parse(times1.get(s).get(0) + ":00").isBefore(LocalTime.parse(times2.get(s).get(0) + ":00")) &&
                        LocalTime.parse(times1.get(s).get(1) + ":00").isAfter(LocalTime.parse(times2.get(s).get(1) + ":00"))) {
                    return true;
                } else if (LocalTime.parse(times1.get(s).get(0) + ":00").isAfter(LocalTime.parse(times2.get(s).get(0) + ":00")) &&
                        LocalTime.parse(times2.get(s).get(1) + ":00").isAfter(LocalTime.parse(times1.get(s).get(0) + ":00"))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates a map of section types with their corresponding section codes of all courses in course_list
     *
     * @param course_list A list of course codes
     * @return A map containing keys of section types that correspond with lists of section codes
     */
    private ArrayList<ArrayList<String>> ConfigureSections(ArrayList<String> course_list) throws Throwable {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (String course : course_list) {
            List<String> section_list = new CourseInformationGenerator(course).getCourseSectionList();
            for (String type : Constants.SECTION_TYPES) {
                ArrayList<String> type_list = new ArrayList<>();
                for (String section : section_list) {
                    section = section.replace("-", "");
                    if (section.substring(0, 2).equals(type)) {
                        type_list.add(section);
                    }
                }
                if (!type_list.isEmpty()) {
                    result.add(type_list);
                }
            }

        }
        return result;
    }
}
