package com.courseApp.courseService;


import com.courseApp.constants.Constants;
import com.courseApp.entity.Schedule;
import com.courseApp.userService.UserRequestProcessor;

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
public class CoursePlanner {

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

    /**
     * STEP ONE: SPLITTING INTO FALL AND SPRING(WINTER)
     *
     */
    private void sort2Schedule(){
        List<String> looping = List.copyOf(this.courseList);
        for(String string: looping){
            if(string.length() > Constants.COURSE_CODE_FLAG){
                String flag = (string.substring(Constants.COURSE_CODE_FLAG -1, Constants.COURSE_CODE_FLAG ));
                if( flag.equals(Constants.FALL_TERM)){
                    this.scheduledF.add(string);
                }
                else if (flag.equals(Constants.WINTER_TERM)) {
                    this.scheduledS.add(string);
                }else {
                    this.scheduledF.add(string);
                    this.scheduledS.add(string);
                }
                this.courseList.remove(string);
            }
        }
    }

    public Schedule generateSchedule(){
        Schedule schedule = new Schedule(this.courseList);
        new ScheduleUpdater().updateScheduleMap(schedule);
        return schedule;

        //    public ArrayList<String> PlanCourseHelper(Schedule schedule, ArrayList<String> courses, ArrayList<String> wishlist) {
//        if (courses.size() == 1 && wishlist.isEmpty()) {
//            String course = courses.get(0);
//            ArrayList<String> current_sections = schedule.getSectionList();
//            ArrayList<String> new_sections = CourseDaoImpl(course).queryCourseSectionList();
//            for (String s : new_sections) {
//                for (String c : current_sections) {
//                    //if (conflicts(s, c)) {
//                    //  break
//                    //}
//                    //else if (c == current_sections.get(current_sections.size()) && conflicts(s, c) == false) {
//                    // return new ArrayList<String>(s)
//                    //}
//                }
//            }
//            throw new Exception(NO_EXISTING_SCHEUDULE); //Someone teach me whats wrong with this
//        }
//        else if (courses.isEmpty()) {
//            try {
//                PlanCourseHelper(schedule, wishlist, new ArrayList<String>());
//            } catch(Exception NO_EXISTING_SCHEDULE) {
//                return new ArrayList<String>();
//            } finally {
//                return PlanCourseHelper(schedule, wishlist, new ArrayList<String>());
//            }
//        }
//        else {
//            ArrayList<String> result = new ArrayList<String>();
//            String course = courses.get(0);
//            ArrayList<String> current_sections = schedule.getSectionList();
//            ArrayList<String> new_sections = CourseDaoImpl(course).queryCourseSectionList();
//            for (String s : new_sections) {
//                for (String c : current_sections) {
//                    if (conflicts(s, c)) {
//                        break;
//                    }
//                    if (c == current_sections.get(current_sections.size()) && conflicts(s, c) == false) {
//                        result.add(s);
//                    }
//                }
//                if (result.isEmpty() == false) {
//                    try {
//                        PlanCourseHelper(schedule, new ArrayList<String>(courses.subList(1, courses.size() - 1)), wishlist);
//                    } catch(Exception NO_EXISTING_SCHEDULE) {
//                        result.clear();
//                    } finally {
//                        result.addAll(PlanCourseHelper(schedule, wishlist, new ArrayList<String>()));
//                        return result;
//                    }
//                }
//            }
//            throw new Exception(NO_EXISTING_SCHEUDULE);
//        }
//    }


//    public static void main(String[] args) throws Throwable {
//        ArrayList<String> th_schedule = new ArrayList<>();
//        th_schedule.add("17:00");
//        th_schedule.add("19:00");
//
//
//        ArrayList<String> tu_schedule = new ArrayList<>();
//        tu_schedule.add("11:00");
//        tu_schedule.add("14:00");
//
//
//        ArrayList<String> th_schedule2 = new ArrayList<>();
//        th_schedule2.add("8:00");
//        th_schedule2.add("9:00");
//
//
//        ArrayList<String> mo_schedule2 = new ArrayList<>();
//        mo_schedule2.add("10:00");
//        mo_schedule2.add("11:00");
//
//        Map<String, ArrayList<String>> day = new HashMap<>();
//        day.put("TH", th_schedule);
//
//
//        Map<String, ArrayList<String>> day2 = new HashMap<>();
//        day2.put("TH", th_schedule2);
//
//        Map<String, ArrayList<String>> day3 = new HashMap<>();
//        day3.put("TU", tu_schedule);
//
//        Map<String, ArrayList<String>> day4 = new HashMap<>();
//        day4.put("MO", mo_schedule2);
//
//        Map<String, Map<String, ArrayList<String>>> cad = new HashMap<>();
//        cad.put("CSC207SLEC0101", day);
//        cad.put("CSC108SLEC0102", day2);
//        cad.put("MAT137YLEC0102", day3);
//        cad.put("CSC209FLEC0102", day4);
//
//        Schedule schedule = new Schedule(cad);
//        System.out.println(new CourseServiceController().getScheduleSummary(schedule));

//        CourseServiceController csc = new CourseServiceController();
//        System.out.println(csc.getSectionInformation("CSC207FLEC0101"));
//    }
    }

}
