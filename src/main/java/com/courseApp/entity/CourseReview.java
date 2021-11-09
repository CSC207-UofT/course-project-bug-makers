package com.courseApp.entity;

import com.courseApp.constants.Constants;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * course review entity for course code, course difficulty rate, course general rate, instructor review map and known instructor list.
 */
public class CourseReview {
    private String courseCode;
    private double courseDifficultyRate;
    private double courseGeneralRate;
    private Map<String, InstReview> instReviewMap;
    private ArrayList<String> instList;

    /**
     * Bson constructor, with instructor review provided, for Course Review Entity.
     *
     * @param courseCode course code w/o section
     * @param courseDifficultyRate course difficulty rate
     * @param courseGeneralRate course general rate
     * @param instReviewMap instructor map
     */
    @BsonCreator
    public CourseReview(@BsonProperty(Constants.COURSE_CODE_DB) String courseCode,
                        @BsonProperty(Constants.COURSE_DIFFICULTY_RATE) double courseDifficultyRate,
                        @BsonProperty(Constants.COURSE_GENERAL_RATE) double courseGeneralRate,
                        @BsonProperty(Constants.INST_REVIEW_MAP) Map<String, InstReview> instReviewMap,
                        @BsonProperty(Constants.INST_LIST) ArrayList<String> instList) {
        this.courseCode = courseCode;
        this.courseDifficultyRate = courseDifficultyRate;
        this.courseGeneralRate = courseGeneralRate;
        this.instReviewMap = instReviewMap;
        this.instList = instList; // Generate instructor list.
    }

    /**
     * Constructor without instructor review provided.
     * The instructor review map will be blank.
     *
     * @param courseCode course code w/o section
     */
    public CourseReview(String courseCode) {
        this.courseCode = courseCode;
        this.courseDifficultyRate = 0.0D;
        this.courseGeneralRate = 0.0D;
        this.instReviewMap = new HashMap<>();
        this.instList = new ArrayList<>();
        this.instList.addAll(instReviewMap.keySet()); // Generate instructor list.
    }


    /**
     * Update the avg. course difficulty rate.
     */
    public void updateCourseDifficultyRate(){
        double res = 0.0D;
        for (var entry: this.instReviewMap.entrySet()){
            res += entry.getValue().getInstDifficultyRate();
        }
        if(res > 0){
        this.setCourseDifficultyRate(res/this.instReviewMap.size());}
    }

    /**
     * Update the avg. course difficulty rate.
     */
    public void updateCourseGeneralRate(){
        double res = 0;
        for (var entry: this.instReviewMap.entrySet()){
            res += entry.getValue().getInstGeneralRate();
        }
        if(res > 0){
            this.setCourseGeneralRate(res/this.instReviewMap.size());}
    }

    /**
     * Return the targeted instructor's review
     *
     * @param instName targeted instructor
     * @return InstReview entity
     */
    public InstReview getSpecificInstReview(String instName){
        return this.instReviewMap.get(instName);
    }

    /**
     * Update the instructor list based on current instructor review map.
     */
    private void updateInstList(){
        ArrayList<String> temp = new ArrayList<>(this.instReviewMap.keySet());
        this.setInstList(temp);
    }

    /**
     * Create a new InstReview entity for course entity.
     *
     * @param instName name of the instructor
     */
    public void createNewInstReview(String instName){
        this.instReviewMap.put(instName, new InstReview(instName));
        this.updateInstList();
        this.updateCourseDifficultyRate();
        this.updateCourseGeneralRate();
    }

    /**
     * Update the course general rate and return it
     *
     * @return updated course general rate
     */
    public double getCourseGeneralRate() {
        this.updateCourseGeneralRate();
        return courseGeneralRate;
    }

    /**
     * Update the course difficulty rate and return it
     *
     * @return updated course difficulty rate
     */

    public double getCourseDifficultyRate() {
        this.updateCourseDifficultyRate();
        return courseDifficultyRate;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseDifficultyRate(double courseDifficultyRate) {
        this.courseDifficultyRate = courseDifficultyRate;
    }

    public void setCourseGeneralRate(double courseGeneralRate) {
        this.courseGeneralRate = courseGeneralRate;
    }

    public Map<String, InstReview> getInstReviewMap() {
        return instReviewMap;
    }

    public void setInstReviewMap(Map<String, InstReview> instReviewMap) {
        this.instReviewMap = instReviewMap;
    }

    public ArrayList<String> getInstList() {
        return instList;
    }

    public void setInstList(ArrayList<String> instList) {
        this.instList = instList;
    }
}
