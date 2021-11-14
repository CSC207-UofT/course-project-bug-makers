package com.courseApp.courseService;

import com.courseApp.constants.Constants;
import com.courseApp.entity.Schedule;

import java.util.ArrayList;
import java.util.Map;


/**
 * Course Service Controller for Present course-related information and course planning.
 */
public class CourseServiceController implements ControlPresentInformation, ControlUserCoursePlanning {


    /**
     * Return string of course information, including title, description, prerequisite and sections.
     *
     * @param courseCode course code
     * @return course information String
     * @throws Throwable exception
     */
    @Override
    public String getCourseGeneralInformation(String courseCode) throws Throwable {
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
    public String getSectionInformation(String courseCodeWSection) throws Throwable {
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
     * <p>
     * Planned schedule should be added to scheduleList iff the planning is not successful.
     * <p>
     * Return the planned schedule list String iff the planning is successful, otherwise, null.
     *
     * @param username username
     * @return schedule list String
     * @throws Throwable exceptions
     */

    @Override
    public String planCourse(String username) throws Throwable {
        return new CoursePlanner(username).generateSchedule().toString();
    }

//    /**
//     * Plan the schedule for user with given username and password.
//     * Course planning will base on courseList, then wishList with courseList at a higher priority
//     * and wishList at a lower priority.
//     *
//     * Planned schedule should be added to scheduleList iff the planning is not successful.
//     *
//     * Return the planned schedule list String iff the planning is successful, otherwise, null.
//     *
//     * @param username username
//     * @return schedule list String
//     * @throws Throwable exceptions
//     */
//
//    @Override
//    public String planCourse(String username) throws Throwable {
//        UserRequestProcessor user = new UserRequestProcessor(username);
//        ArrayList<ArrayList<String>> courses = ConfigureSections(user.queryUserCourseList());
//        try {
//            planScheduleList(new ArrayList<>(), courses);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ArrayList<ArrayList<String>> result = planScheduleList(new ArrayList<>(), courses);
//        Schedule schedule = new Schedule(result.get(0));
//        new ScheduleUpdater().updateScheduleMap(schedule);
//        user.insertOneSchedule(schedule);
//        return schedule.toString();
//    }
//
//
//    /**
//     * Returns a list of valid (without time conflicts) schedules from existing schedules in schedule_list that
//     * contains all sections in section_list
//     *
//     * @param schedule_list List of schedule entities
//     * @param section_list ArrayList of strings representing courses with section codes
//     * @return ArrayList of course codes
//     * @throws Throwable exceptions
//     */
//    public ArrayList<ArrayList<String>> planScheduleList(ArrayList<ArrayList<String>> schedule_list,
//                                                         List<ArrayList<String>> section_list) throws Throwable {
//        ArrayList<ArrayList<String>> result = new ArrayList<>();
//        for (ArrayList<String> schedule : schedule_list) {
//            ArrayList<String> new_schedule = new ArrayList<>(schedule);
//            for (String section : section_list.get(0)) {
//                for (String schedule_section : schedule) {
//                    if (CheckConflict(schedule_section, section)) {
//                        break;
//                    }
//                    else if (schedule_section.equals(schedule.get(schedule.size() - 1))) {
//                        new_schedule.add(section);
//                        result.add(new_schedule);
//                    }
//                }
//            }
//        }
//        if (result.isEmpty()) {
//            throw new Exception(Exceptions.NO_EXISTING_SCHEDULE);
//        }
//        else if (section_list.size() == 1) {
//            return result;
//        }
//        else {
//            try {
//                planScheduleList(result, section_list.subList(1, section_list.size() - 1));
//            }
//            catch (Exception  NO_EXISTING_SCHEDULE) {
//                throw new Exception(NO_EXISTING_SCHEDULE);
//            }
//            return planScheduleList(result, section_list.subList(1, section_list.size() - 1));
//        }
//    }
//
//
//
//
//    /**
//     * Check if two sections occur at the same time
//     *
//     * @param section1: A section of a course
//     * @param section2: A section of a course
//     * @return True iff the sections have overlapping times
//     */
//    public boolean CheckConflict(String section1, String section2) throws Throwable {
//        if (section1.charAt(6) != section2.charAt(6)) {
//            if (section1.charAt(6) != 'Y' && section2.charAt(6) != 'Y') {
//                return true;
//            }
//        }
//        try {
//            new CourseInformationGenerator(section1);
//            new CourseInformationGenerator(section2);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        CourseInformationGenerator cig1 = new CourseInformationGenerator(section1);
//        CourseInformationGenerator cig2 = new CourseInformationGenerator(section2);
//        Map<String, ArrayList<String>> times1 = cig1.getSectionSpecificSchedule();
//        Map<String, ArrayList<String>> times2 = cig2.getSectionSpecificSchedule();
//        for (String s : times1.keySet()) {
//            if (times2.containsKey(s)) {
//                if (LocalTime.parse(times1.get(s).get(0) + ":00").isBefore(LocalTime.parse(times2.get(s).get(0) + ":00")) &&
//                        LocalTime.parse(times1.get(s).get(1) + ":00").isAfter(LocalTime.parse(times2.get(s).get(1) + ":00"))) {
//                    return true;
//                } else if (LocalTime.parse(times1.get(s).get(0) + ":00").isAfter(LocalTime.parse(times2.get(s).get(0) + ":00")) &&
//                        LocalTime.parse(times2.get(s).get(1) + ":00").isAfter(LocalTime.parse(times1.get(s).get(0) + ":00"))) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Creates a map of section types with their corresponding section codes of all courses in course_list
//     *
//     * @param course_list A list of course codes
//     * @return A map containing keys of section types that correspond with lists of section codes
//     */
//    public ArrayList<ArrayList<String>> ConfigureSections(ArrayList<String> course_list) throws Throwable {
//        ArrayList<ArrayList<String>> result = new ArrayList<>();
//        for (String course : course_list) {
//            List<String> section_list = new CourseInformationGenerator(course).getCourseSectionList();
//            for (String type : Constants.SECTION_TYPES) {
//                ArrayList<String> type_list = new ArrayList<>();
//                for (String section : section_list) {
//                    section = section.replace("-", "");
//                    if (section.substring(0,2).equals(type)) {
//                        type_list.add(section);
//                    }
//                }
//                if (!type_list.isEmpty()) {
//                    result.add(type_list);
//                }
//            }
//
//        }
//        return result;
//    }

//    public static void main(String[] args) throws Throwable {
//        CourseServiceController csc = new CourseServiceController();
//        System.out.println(csc.getSectionInformation("CSC207FLEC0101"));
//        System.out.println(csc.planCourse("CalendarTest2"));
//    }

}
