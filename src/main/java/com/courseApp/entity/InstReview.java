package com.courseApp.entity;

import com.courseApp.constants.Constants;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.ArrayList;

/**
 * InstReview entity for instructor name, instructor difficulty rate, instructor general rate,
 * instructor recommendation rate and a list of userReview entities.
 *
 */
public class InstReview {
    private String instructorName;
    private double instGeneralRate;
    private double instDifficultyRate;
    private double instRecommendationScore;
    private ArrayList<UserReview> userReviewList;

    /**
     * Bson constructor for InstReview Entity
     *
     * @param instructorName Bson instructorName
     * @param instGeneralRate Bson instGeneralRate
     * @param instDifficultyRate Bson instDifficultyRate
     * @param instRecommendationScore Bson instRecommendationScore
     * @param userReviewList Bson userReviewList
     */
    @BsonCreator
    @PersistenceConstructor
    public InstReview(@BsonProperty(Constants.INST_NAME) String instructorName,
                      @BsonProperty(Constants.INST_GENERAL_RATE) double instGeneralRate,
                      @BsonProperty(Constants.INST_DIFFICULTY_RATE) double instDifficultyRate,
                      @BsonProperty(Constants.INST_RECOMMENDATION_SCORE) double instRecommendationScore,
                      @BsonProperty(Constants.USER_REVIEW_LIST) ArrayList<UserReview> userReviewList) {
        this.instructorName = instructorName;
        this.instGeneralRate = instGeneralRate;
        this.instDifficultyRate = instDifficultyRate;
        this.instRecommendationScore = instRecommendationScore;
        this.userReviewList = userReviewList;
    }

    /**
     * Constructor for instantiating raw instructor review
     *
     * @param instructorName name of the instructor
     */
    public InstReview(String instructorName) {
        this.instructorName = instructorName;
        this.instGeneralRate = 0.0F;
        this.instDifficultyRate = 0.0F;
        this.instRecommendationScore = 0.0F;
        this.userReviewList = new ArrayList<>();
    }

    /**
     * Update instructor's general rate.
     */
    public void updateInstructorGeneralRate(){
        double res = 0D;
        for (UserReview ur: this.userReviewList){
            res += ur.getGeneralRate();
        }
        this.setInstGeneralRate(res/this.userReviewList.size());
    }

    /**
     * Update instructor's difficulty rate.
     */
    public void updateInstructorDifficultyRate(){
        double res = 0D;
        for (UserReview ur: this.userReviewList){
            res += ur.getDifficultyRate();
        }
        this.setInstDifficultyRate(res/this.userReviewList.size());
    }

    /**
     * Update instructor's recommendation score.
     */
    public void updateInstructorRecommendationScore(){
        double res = 0D;
        for (UserReview ur: this.userReviewList){
            res += ur.getRecommendationScore();
        }
        this.setInstRecommendationScore(res/this.userReviewList.size());
    }

    /**
     * Get the number of review under this instructor.
     *
     * @return the length of the review list.
     */
    public int getReviewNumber(){
        return this.userReviewList.size();
    }

    /**
     * Get specific user review by a username.
     *
     * @return UserReview entity
     */
    public UserReview getSpecificUserReview(String username){
        for(UserReview ur: this.userReviewList){
            if(ur.getUsername().equals(username)){
                return ur;
            }
        }
        return null;
    }


    public String getInstructorName() {
        return instructorName;
    }

    public double getInstGeneralRate() {
        return instGeneralRate;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public double getInstDifficultyRate() {
        return instDifficultyRate;
    }

    public double getInstRecommendationScore() {
        return instRecommendationScore;
    }

    public ArrayList<UserReview> getUserReviewList() {
        return userReviewList;
    }

    public void setInstGeneralRate(double instGeneralRate) {
        this.instGeneralRate = instGeneralRate;
    }

    public void setInstDifficultyRate(double instDifficultyRate) {
        this.instDifficultyRate = instDifficultyRate;
    }

    public void setInstRecommendationScore(double instRecommendationScore) {
        this.instRecommendationScore = instRecommendationScore;
    }

    public void setUserReviewList(ArrayList<UserReview> userReviewList) {
        this.userReviewList = userReviewList;
        this.updateInstructorDifficultyRate();
        this.updateInstructorGeneralRate();
        this.updateInstructorRecommendationScore();
    }
}
