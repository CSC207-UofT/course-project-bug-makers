package com.courseApp.courseService;


import com.courseApp.constants.Constants;
import com.courseApp.constants.Exceptions;
import com.courseApp.entity.Schedule;
import com.courseApp.userService.UserRequestProcessor;
import com.courseApp.utils.SectionTool;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<ArrayList<SectionTool>> sectionList;

    public CoursePlanner(String username) throws Throwable {
        this.username = username;
        this.courseList = new UserRequestProcessor(username).queryUserCourseList();
        this.wishList = new UserRequestProcessor(username).queryUserWishList();
        this.scheduledF = new ArrayList<>();
        this.scheduledS = new ArrayList<>();
        this.sectionList = CreateSectionList(this.courseList);
//        this.sort2Schedule();
    }

    public Schedule generateSchedule() throws Throwable {
        UserRequestProcessor user = new UserRequestProcessor(username);
        try {
            planScheduleList(new ArrayList<>(), this.sectionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<ArrayList<SectionTool>> scheduleList = planScheduleList(new ArrayList<>(), this.sectionList);
        for (ArrayList<SectionTool> schedule : scheduleList) {
            ArrayList<String> string_schedule = new ArrayList<>();
            for (SectionTool section : schedule) {
                string_schedule.add(section.getSectionCode());
            }
            result.add(string_schedule);
        }
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
    private ArrayList<ArrayList<SectionTool>> planScheduleList(ArrayList<ArrayList<SectionTool>> schedule_list,
                                                               ArrayList<ArrayList<SectionTool>> section_list) throws Throwable {
        ArrayList<ArrayList<SectionTool>> result = new ArrayList<>();
        if (schedule_list.isEmpty()) {
            for (SectionTool section : section_list.get(0)) {
                ArrayList<SectionTool> sublist = new ArrayList<>();
                sublist.add(section);
                result.add(sublist);
            }
        }
        else for (ArrayList<SectionTool> schedule : schedule_list) {
            ArrayList<SectionTool> new_schedule = new ArrayList<>(schedule);
            for (SectionTool section : section_list.get(0)) {
                for (SectionTool schedule_section : schedule) {
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
                planScheduleList(result, new ArrayList<>(section_list.subList(1, section_list.size())));
            } catch (Exception NO_EXISTING_SCHEDULE) {
                throw new Exception(NO_EXISTING_SCHEDULE);
            }
            return planScheduleList(result, new ArrayList<>(section_list.subList(1, section_list.size())));
        }
    }

    /**
     * Check if two sections occur at the same time
     *
     * @param section1: A section of a course
     * @param section2: A section of a course
     * @return True iff the sections have overlapping times
     */
    private boolean CheckConflict(SectionTool section1, SectionTool section2) {
        if (section1.getSectionCode().charAt(6) != section2.getSectionCode().charAt(6)) {
            if (section1.getSectionCode().charAt(6) != 'Y' && section2.getSectionCode().charAt(6) != 'Y') {
                return false;
            }
        }
        for (String s : section1.getScheduleMap().keySet()) {
            if (section2.getScheduleMap().containsKey(s)) {
                if (LocalTime.parse(section1.getScheduleMap().get(s).get(0) +
                        ":00").isBefore(LocalTime.parse(section2.getScheduleMap().get(s).get(0) + ":00")) &&
                        LocalTime.parse(section1.getScheduleMap().get(s).get(1) +
                                ":00").isAfter(LocalTime.parse(section2.getScheduleMap().get(s).get(1) + ":00"))) {
                    return true;
                } else if (LocalTime.parse(section1.getScheduleMap().get(s).get(0) +
                        ":00").isAfter(LocalTime.parse(section2.getScheduleMap().get(s).get(0) + ":00")) &&
                        LocalTime.parse(section2.getScheduleMap().get(s).get(1) +
                                ":00").isAfter(LocalTime.parse(section1.getScheduleMap().get(s).get(0) + ":00"))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates a map of section types with their corresponding section codes of all courses in course_list
     *
     * @param course_list A list of course codes with sections
     * @return An ArrayList containing lists of sections sorted by type
     */
    private ArrayList<ArrayList<SectionTool>> CreateSectionList(ArrayList<String> course_list) throws Throwable {
        ArrayList<ArrayList<SectionTool>> result = new ArrayList<>();
        ArrayList<String> course_codes = new ArrayList<>();
        ArrayList<String> course_list_new = new ArrayList<>();
        for (String course : course_list) {
            if (!course_codes.contains(course.substring(0, 7))) {
                course_codes.add(course.substring(0, 7));
                course_list_new.add(course);
            }

        }
        for (String course : course_list_new) {
            List<String> section_list = new CourseInformationGenerator(course).getCourseSectionList();
            for (String type : Constants.SECTION_TYPES) {
                ArrayList<SectionTool> type_list = new ArrayList<>();
                for (String s : section_list) {
                    s = course.substring(0, 7) + s.replace("-", "");
                    SectionTool section = new SectionTool(s, new CourseInformationGenerator(s).getSectionSpecificSchedule());
                    if (section.getSectionType().equals(type)) {
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
