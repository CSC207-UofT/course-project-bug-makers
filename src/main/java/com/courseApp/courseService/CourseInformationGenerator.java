package com.courseApp.courseService;

import com.courseApp.dao.CourseDaoImpl;
import com.courseApp.constants.Constants;
import com.courseApp.constants.Exceptions;
import com.courseApp.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CourseInformationGenerator Use Case for querying course information.
 */
public class CourseInformationGenerator implements UseCourseBasicInfo, UseSectionSpecificScheduleInfo {
    private final String section;
    private final Course courseEntity;

    /**
     * Constructing a course information generator.
     *
     * @param courseCodeWithSection course code (may or may not include the section, if with section provided, section
     *                              specific detail will be available)
     * @throws Throwable Json exception/no section code exception
     */
    public CourseInformationGenerator(String courseCodeWithSection) throws Throwable {
        this.section = getSectionCode(courseCodeWithSection);
        this.courseEntity = setCourseEntity(setCourseCode(courseCodeWithSection));
    }

    /**
     * Partition on the course code, extract the department code + course number + term flag part.
     *
     * @param courseCodeWithSection course code complex input
     * @return extracted department code + course number + term flag part
     */
    private String setCourseCode(String courseCodeWithSection){
        return courseCodeWithSection.substring(0, Constants.COURSE_CODE_FLAG);
    }

    /**
     * Partition on the course code, extract the section code iff section code exists. Otherwise, return null.
     *
     * @param courseCodeWithSection course code complex input
     * @return extracted section code iff  section code exists
     */
    private String getSectionCode(String courseCodeWithSection){
        return courseCodeWithSection.length() == Constants.COURSE_CODE_W_SECTION_LENGTH ?
                courseCodeWithSection.substring(Constants.COURSE_CODE_FLAG,
                        Constants.COURSE_CODE_W_SECTION_LENGTH) : null;
    }

    /**
     * Query course entity on the course code.
     *
     * @param courseCode course code
     * @return quarried course entity
     * @throws Throwable if the course is not found by DAO
     */
    private Course setCourseEntity(String courseCode) throws Throwable {
        return new CourseDaoImpl(courseCode).generateCourseEntity();
    }


    /**
     * Get the course title.
     *
     * @return course title
     */
    @Override
    public String getCourseTitle() {
        return this.courseEntity.getCourseTitle();
    }

    /**
     * Get the course code (i.e., department code + course code + term flag).
     *
     * @return course code
     */
    @Override
    public String getCourseCode() {
        return this.courseEntity.getCourseCode();
    }

    /**
     * Get the course description.
     *
     * @return course description
     */
    @Override
    public String getCourseDescription() {
        return this.courseEntity.getCourseDescription();
    }

    /**
     * Get the course term.
     *
     * @return course term
     */
    @Override
    public String getCourseTerm() {
        return this.courseEntity.getCourseTerm();
    }


    /**
     * Get the course pre-req.
     *
     * @return course pre-req
     */
    @Override
    public String getCoursePrerequisite() {
        return this.courseEntity.getCoursePrerequisite();
    }

    /**
     * Get the section list of the course.
     *
     * @return course section list
     */
    @Override
    public List<String> getCourseSectionList() {
        return this.courseEntity.getCourseSectionList();
    }

    /**
     * Get the schedule map for all sections.
     *
     * Note that the structure be like: Map(section -> Map(weekday -> ArrayList(timing)))
     *
     * @return map of schedule for all sections.
     */
    @Override
    public Map<String, Map<String, ArrayList<String>>> getCourseSectionScheduleMap () {
        return this.courseEntity.getCourseSectionScheduleMap();
    }

    /**
     * Get the section type list for all section.
     *
     * @return List of section types in the order of section list.
     */

    @Override
    public List<String> getCourseSectionType() {
        List<String> res = this.getCourseSectionList();
        for(int i = 0; i < res.size(); i++){
            res.set(i, res.get(i).substring(0, Constants.COURSE_CODE_SECTION_FLAG));
        }
        return res;

    }


    /**
     * Get the schedule map for a single section with section by provided section code.
     *
     * @return map of schedule for all sections
     * @throws Throwable No section code exception
     */
    @Override
    public Map<String, ArrayList<String>> getSectionSpecificSchedule() throws Throwable {
        if (this.section != null){
            return this.getCourseSectionScheduleMap().get(this.section);
        } else {
            throw new Throwable(Exceptions.NO_SECTION_CODE);
        }

    }



//    public static void main(String[] args) throws Throwable {
//        CourseInformationGenerator cig = new CourseInformationGenerator("CSC207F");
//        System.out.println(cig.getCourseSectionType());
//    }

}
