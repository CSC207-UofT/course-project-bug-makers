package com.courseApp.entity;

import java.util.ArrayList;

/**
 * InstReview entity for instructor name, instructor difficulty rate, instructor general rate,
 * instructor recommendation rate and a list of userReview eneties.
 *
 */
public class InstReview {
    private final String instructorName;
    private float instGeneralRate;
    private float instDifficultyRate;
    private float instRecommendationScore;
    private ArrayList<UserReview> userReviewList;

    public InstReview(String instructorName, float instGeneralRate, float instDifficultyRate, float instRecommendationScore, ArrayList<UserReview> userReviewList) {
        this.instructorName = instructorName;
        this.instGeneralRate = instGeneralRate;
        this.instDifficultyRate = instDifficultyRate;
        this.instRecommendationScore = instRecommendationScore;
        this.userReviewList = userReviewList;
    }

    /**
     * Update instructor's general rate.
     */
    public void updateInstructorGeneralRate(){
        float res = 0;
        for (UserReview ur: this.userReviewList){
            res += ur.getGeneralRate();
        }
        this.setInstGeneralRate(res/this.userReviewList.size());
    }

    /**
     * Update instructor's difficulty rate.
     */
    public void updateInstructorDifficultyRate(){
        float res = 0;
        for (UserReview ur: this.userReviewList){
            res += ur.getDifficultyRate();
        }
        this.setInstDifficultyRate(res/this.userReviewList.size());
    }

    /**
     * Get the number of review under this instructor.
     *
     * @return the length of the review list.
     */
    public int getReviewNumber(){
        return this.userReviewList.size();
    }

    public String getInstructorName() {
        return instructorName;
    }

    public float getInstGeneralRate() {
        return instGeneralRate;
    }

    public float getInstDifficultyRate() {
        return instDifficultyRate;
    }

    public float getInstRecommendationScore() {
        return instRecommendationScore;
    }

    public ArrayList<UserReview> getUserReviewList() {
        return userReviewList;
    }

    public void setInstGeneralRate(float instGeneralRate) {
        this.instGeneralRate = instGeneralRate;
    }

    public void setInstDifficultyRate(float instDifficultyRate) {
        this.instDifficultyRate = instDifficultyRate;
    }

    public void setInstRecommendationScore(float instRecommendationScore) {
        this.instRecommendationScore = instRecommendationScore;
    }

    public void setUserReviewList(ArrayList<UserReview> userReviewList) {
        this.userReviewList = userReviewList;
    }
}
