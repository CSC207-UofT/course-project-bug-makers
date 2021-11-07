package com.courseApp.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * course review entity for course code, course difficulty rate, course general rate, instructor review map and known instructor list.
 */
public class CourseReview {
    private final String courseCode;
    private float courseDifficultyRate;
    private float courseGeneralRate;
    private Map<String, InstReview> instReviewMap;
    private ArrayList<String> instList;

    /**
     * Constructor with instructor review provided.
     *
     * @param courseCode course code w/o section
     * @param courseDifficultyRate course difficulty rate
     * @param courseGeneralRate course general rate
     * @param instReviewMap instructor map
     */
    public CourseReview(String courseCode, float courseDifficultyRate, float courseGeneralRate, Map<String, InstReview> instReviewMap) {
        this.courseCode = courseCode;
        this.courseDifficultyRate = courseDifficultyRate;
        this.courseGeneralRate = courseGeneralRate;
        this.instReviewMap = instReviewMap;
        this.instList = new ArrayList<>();
        this.instList.addAll(instReviewMap.keySet()); // Generate instructor list.
    }

    /**
     * Constructor without instructor review provided.
     * The instructor review map will be blank.
     *
     * @param courseCode course code w/o section
     * @param courseDifficultyRate course difficulty rate
     * @param courseGeneralRate course general rate
     */
    public CourseReview(String courseCode, float courseDifficultyRate, float courseGeneralRate) {
        this.courseCode = courseCode;
        this.courseDifficultyRate = courseDifficultyRate;
        this.courseGeneralRate = courseGeneralRate;
        this.instReviewMap = new HashMap<>();
        this.instList = new ArrayList<>();
        this.instList.addAll(instReviewMap.keySet()); // Generate instructor list.
    }


    /**
     * Update the avg. course difficulty rate.
     */
    public void updateCourseDifficultyRate(){
        float res = 0;
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
        float res = 0;
        for (var entry: this.instReviewMap.entrySet()){
            res += entry.getValue().getInstGeneralRate();
        }
        if(res > 0){
            this.setCourseGeneralRate(res/this.instReviewMap.size());}
    }


    public String getCourseCode() {
        return courseCode;
    }

    public float getCourseDifficultyRate() {
        return courseDifficultyRate;
    }

    public void setCourseDifficultyRate(float courseDifficultyRate) {
        this.courseDifficultyRate = courseDifficultyRate;
    }

    public float getCourseGeneralRate() {
        return courseGeneralRate;
    }

    public void setCourseGeneralRate(float courseGeneralRate) {
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
