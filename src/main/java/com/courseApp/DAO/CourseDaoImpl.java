package com.courseApp.DAO;

import com.courseApp.constants.Exceptions;
import com.courseApp.entity.Course;
import com.courseApp.utils.UrlReader;
import com.courseApp.constants.Constants;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.net.URL;
import java.util.*;

/**
 * An implemented CourseDao class. It could query data from API and generate a course entity by course code.
 * Note that course code should be in format of [3 char dept code][3 digit three num][term flag], i.e., CSC207F.
 */
public class CourseDaoImpl implements CourseDAO {

    private final String courseCode;
    private final String courseTerm;
    private Map<String, Object> map;
    private URL url;

    public CourseDaoImpl(String courseCode) throws Throwable {

        this.courseCode = courseCode;
        this.courseTerm = this.courseCode.substring(this.courseCode.length() - 1);
        this.setMap();
    }

    @Override
    public Course generateCourseEntity()  {

        return new Course(this.courseCode,
                this.queryCourseTitle(),
                this.queryCourseDescription(),
                this.queryCourseSectionList(),
                this.queryCourseSectionScheduleMap(),
                this.courseTerm);

    }


    /**
     * Return URL for querying the course information.
     * courseCode must end with the term indicator, i.e., F/S/Y,
     * otherwise throw WRONG_COURSE_CODE_FORMAT exception.
     */
    @Override
    public void generateQuery() throws Throwable {
        if (this.courseTerm.equals("S") | this.courseTerm.equals("F") | this.courseTerm.equals("Y")) {
            String res = Constants.UT_API_URL.replace(Constants.COURSE_CODE, this.courseCode.substring(0,
                    this.courseCode.length() - 1));
            this.url = new URL(res.replace(Constants.COURSE_TERM, this.courseTerm));
        } else {
            throw new Throwable(Exceptions.WRONG_COURSE_CODE_FORMAT);
        }
    }


    /**
     * Setter method for converting Json response from the API to a map instance variable.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setMap() throws Throwable {
        this.generateQuery();
        String webContent = UrlReader.read(this.url);
        this.map = new Gson().fromJson(JsonParser.parseString(webContent), Map.class);
        this.map = (Map<String, Object>) this.map.get(this.map.keySet().toArray()[0]);
    }

    /**
     * Getter Method for map instance variable, where map is the Json response from the API.
     *
     * @return map type of json file of the API response
     */
    @Override
    public Map<String, Object> getMap() {
        return this.map;
    }

    /**
     * Query method for getting the course term.
     *
     * @return Course Term
     */
    @Override
    public String queryCourseTerm() {
        return this.courseTerm;
    }

    /**
     * Query method for getting the course description
     *
     * @return Course Term
     */
    @Override
    public String queryCourseDescription() {
        return (String) this.map.get(Constants.COURSE_DESCRIPTION);
    }

    /**
     * Query method for getting the course Title
     *
     * @return Course Title
     */
    @Override
    public String queryCourseTitle() {
        return (String) this.map.get(Constants.COURSE_TITLE);
    }

    /**
     * Query method for getting the course sections, including TUT LEC and PRA sections.
     *
     * @return ArrayList of all sections.
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> queryCourseSectionList() {
        Set<String> res = ((Map<String, Object>) this.map.get(Constants.MEETING)).keySet();
        return new ArrayList<>(res);
    }


    /** Query method, returns the schedule for each section.
     *
     * @return schedule for each section.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Map<String, ArrayList<String>>> queryCourseSectionScheduleMap() {
        Map<String, Object> sectionMap = (Map<String, Object>) this.map.get(Constants.MEETING);
        Map<String, Map<String, ArrayList<String>>> resMap = new HashMap<>();

        for (String section : this.queryCourseSectionList()) {
            // loop over the sections under the course
            Map<String, Object> sectionDetail = (Map<String, Object>) ((Map<String, Object>)
                    sectionMap.get(section)).get(Constants.SCHEDULE);
            Map<String, ArrayList<String>> sectionSpecific = new HashMap<>();
            // loop over the meeting time
            for (var entry : sectionDetail.entrySet()){
                ArrayList<String> meetingTimes = new ArrayList<>();
                meetingTimes.add( (String)((Map<String, Object>)entry.getValue()).get(Constants.MEETING_START_TIME));
                meetingTimes.add( (String) ((Map<String, Object>)entry.getValue()).get(Constants.MEETING_END_TIME));
                sectionSpecific.put((String)((Map<String, Object>)
                        entry.getValue()).get(Constants.MEETING_DAY), meetingTimes);
            }
            resMap.put(section, sectionSpecific);


        }
        return resMap;
    }



//    public static void main(String[] args) throws Throwable {
////        System.out.println(1);
////        CourseDaoImpl cdi = new CourseDaoImpl("CSC207F");
////        cdi.setMap();
////        System.out.println(cdi.queryCourseSectionScheduleMap());
////
////
////        ArrayList<String> th_schedule = new ArrayList<>();
////        th_schedule.add("17:00");
////        th_schedule.add("18:00");
////
////
////        ArrayList<String> tu_schedule = new ArrayList<>();
////        tu_schedule.add("11:00");
////        tu_schedule.add("12:00");
////
////        Map<String, ArrayList<String>> day = new HashMap<>();
////        day.put("TH", th_schedule);
////        day.put("TU", tu_schedule);
////
////        Map<String, Map<String, ArrayList<String>>> cad = new HashMap<>();
////        cad.put("CSC207SLEC0101", day);
////
////        System.out.println(cad);
//
//        CourseDaoImpl cdi = new CourseDaoImpl("CSC207F");
//        Course a = cdi.generateCourseEntity();
//        System.out.println(a);
//    }
}
