package com.courseApp.entity;

import com.courseApp.constants.Constants;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.sql.Timestamp;

import static com.courseApp.constants.Constants.GENERAL_RATE;

/**
 * User Review entity for username, general rate, difficulty rate, timestamp and review.
 */
public class UserReview {
     private  String username;
     private  float generalRate;
     private  float difficultyRate;
     private  float recommendationScore;
     private  String reviewString;
     private  Timestamp timestamp;

    /**
     * Bson constructor for creating a UserReview entity.
     *
     * @param username Bson username
     * @param generalRate Bson generalRate
     * @param difficultyRate Bson difficultyRate
     * @param recommendationScore Bson recommendationScore
     * @param reviewString Bson reviewString
     */
     @BsonCreator
    public UserReview(@BsonProperty(Constants.USERNAME)  String username,
                      @BsonProperty(Constants.GENERAL_RATE) float generalRate,
                      @BsonProperty(Constants.DIFFICULTY_RATE) float difficultyRate,
                      @BsonProperty(Constants.RECOMMENDATION_SCORE) float recommendationScore,
                      @BsonProperty(Constants.REVIEW_STRING) String reviewString) {
        this.generalRate = generalRate;
        this.difficultyRate = difficultyRate;
        this.recommendationScore = recommendationScore;
        this.username = username;
        this.reviewString = reviewString;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public float getGeneralRate() {
        return generalRate;
    }

    public float getRecommendationScore() {
        return recommendationScore;
    }

    public float getDifficultyRate() {
        return difficultyRate;
    }

    public String getUsername() {
        return username;
    }

    public String getReviewString() {
        return reviewString;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGeneralRate(float generalRate) {
        this.generalRate = generalRate;
    }

    public void setDifficultyRate(float difficultyRate) {
        this.difficultyRate = difficultyRate;
    }

    public void setRecommendationScore(float recommendationScore) {
        this.recommendationScore = recommendationScore;
    }

    public void setReviewString(String reviewString) {
        this.reviewString = reviewString;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
