package com.courseApp.dao;

import com.courseApp.constants.Exceptions;
import com.courseApp.entity.Course;
import com.courseApp.utils.UrlReader;
import com.courseApp.constants.Constants;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.net.URL;
import java.util.*;

import static com.courseApp.constants.Constants.*;

/**
 * An implemented CourseDao class. It could query data from API and generate a course entity by course code.
 * Note that course code should be ControlPresentInfo format of [3 char dept code][3 digit three num][term Tag], i.e., CSC207F.
 */
public class CourseDaoImpl implements CourseDAO {

    private final String courseCode;
    private final String courseTerm;
    private Map<String, Object> map;
    private URL url;


    /**
     * Construct a CourseDaoImpl instance.
     *
     * @param courseCode courseCode of Department Code + Course Number + Term Tag
     * @throws Throwable raise error if the information does not exist.
     */
    public CourseDaoImpl(String courseCode) throws Throwable {

        this.courseCode = courseCode;
        this.courseTerm = this.courseCode.substring(this.courseCode.length() - 1);
        this.setMap();
    }

    /**
     * Generate a course entity.
     * @return Course (an entity)
     */
    @Override
    public Course generateCourseEntity()  {

        return new Course(this.courseCode,
                this.queryCourseTitle(),
                this.queryCourseDescription(),
                this.queryCourseSectionList(),
                this.queryCourseSectionScheduleMap(),
                this.courseTerm,
                this.queryCoursePrerequisite(), this.querySectionInstructorMap());

    }


    /**
     * Set URL for querying the course information.
     * courseCode must end with the term indicator, i.e., F/S/Y,
     * otherwise throw WRONG_COURSE_CODE_FORMAT exception.
     */
    private void generateQuery() throws Throwable {
        if (this.courseTerm.equals(Constants.WINTER_TERM) | this.courseTerm.equals(Constants.FALL_TERM) | this.courseTerm.equals(Constants.YEAR)) {
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
    @SuppressWarnings({"unchecked", "SuspiciousMethodCalls"})
    private void setMap() throws Throwable {
        this.generateQuery();
        String webContent = UrlReader.read(this.url);
        this.map = new Gson().fromJson(JsonParser.parseString(webContent), Map.class);
        this.map = (Map<String, Object>) this.map.get(this.map.keySet().toArray()[0]);
    }

    /**
     * Getter method for getting map instance variable, where map is the Json response from the API.
     *
     * @return map type of json file of the API response
     */
    @Override
    public Map<String, Object> getMap() {
        return this.map;
    }


    /**
     * Query method for getting the course pre-req.
     *
     * @return Course pre-req string
     */
    @Override
    public String queryCoursePrerequisite() {
        return (String) this.map.get(Constants.PREREQUISITE);
    }

    /**
     * Query method for getting the course description
     *
     * @return Course Term
     */
    @Override
    public String queryCourseDescription() {
        return procDescription((String) this.map.get(Constants.COURSE_DESCRIPTION));
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
    public ArrayList<String> queryCourseSectionList() {
        Set<String> res = ((Map<String, Object>) this.map.get(Constants.MEETING)).keySet();
        return new ArrayList<>(res);
    }


    /** Query method, returns the schedule for each section.
     *
     * Structure should be like: Map(section -> Map(weekday -> ArrayList(timing)))
     *
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
            for (Map.Entry<String, Object> entry : sectionDetail.entrySet()){
                ArrayList<String> meetingTimes = new ArrayList<>();
                meetingTimes.add( (String)((Map<String, Object>)entry.getValue()).get(Constants.MEETING_START_TIME));
                meetingTimes.add( (String) ((Map<String, Object>)entry.getValue()).get(Constants.MEETING_END_TIME));
                sectionSpecific.put((String)((Map<String, Object>)
                        entry.getValue()).get(Constants.MEETING_DAY), meetingTimes);
            }
            resMap.put(procSection(section), sectionSpecific);
        }
        return resMap;
    }

    /**
     * Query the instructor name for each section.
     *
     * @return Map of section as key and Instructor name as value.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String> querySectionInstructorMap() {
        Map<String, Object> sectionMap = (Map<String, Object>) this.map.get(Constants.MEETING);
        Map<String, String> resMap = new HashMap<>();

        for (String section : this.queryCourseSectionList()){
            // loop over the sections under the course
            if (section.contains(LEC_FLAG)){
            Map<String, Object> sectionDetail = (Map<String, Object>) ((Map<String, Object>)
                    sectionMap.get(section)).get(Constants.INSTRUCTOR);
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> entry: sectionDetail.entrySet()){
                // loop over the instructor list
                sb.append(((Map<String, Object>)  entry.getValue()).get(Constants.FIRST_NAME));
                sb.append(Constants.INSTRUCTOR_SPLIT);
                sb.append(((Map<String, Object>)  entry.getValue()).get(Constants.LAST_NAME));
                sb.append(COMMA);
            }
            resMap.put(procSection(section), sb.toString().replaceFirst(LAST_REG,""));}
        }

        return resMap;
    }

    /**
     * Process the section string, return the section string with SECTION_MAKER omitted.
     * @param section  Section string
     * @return Edited section string with SECTION_MAKER omitted
     */
    private String procSection(String section){
        return section.replace(Constants.SECTION_MARKER, "");
    }

    /**
     * Process the description string, return the modified description string with DESCRIPTION formatter omitted.
     * @param description Description string
     * @return Edited description string with formatter omitted.getCourseSectionScheduleMap().entrySet()
     */
    private String procDescription(String description){

        return description.replace(Constants.DESCRIPTION_FORMATTER_1,
                "").replace(Constants.DESCRIPTION_FORMATTER_2, "");
    }



//    public static void main(String[] args) throws Throwable {
//        System.out.println(1);
//        CourseDaoImpl cdi = new CourseDaoImpl("CSC207F");
//        Map<String, String> res = cdi.querySectionInstructorMap();
//        System.out.println(res);
//    }




}
