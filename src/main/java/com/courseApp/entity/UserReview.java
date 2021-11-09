package com.courseApp.entity;

import com.courseApp.constants.Constants;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import static com.courseApp.constants.Constants.GENERAL_RATE;

/**
 * User Review entity for username, general rate, difficulty rate, timestamp and review.
 */
public class UserReview {
     private  String username;
     private  double generalRate;
     private  double difficultyRate;
     private  double recommendationScore;
     private  String reviewString;
     private Date timestamp;

    /**
     * Bson constructor for creating a UserReview entity.
     *
     * @param username Bson username
     * @param generalRate Bson generalRate
     * @param difficultyRate Bson difficultyRate
     * @param recommendationScore Bson recommendationScore
     * @param reviewString Bson reviewString
     * @param timestamp Bson timestamp
     */
     @BsonCreator
    public UserReview(@BsonProperty(Constants.USERNAME) String username,
                      @BsonProperty(Constants.GENERAL_RATE) double generalRate,
                      @BsonProperty(Constants.DIFFICULTY_RATE) double difficultyRate,
                      @BsonProperty(Constants.RECOMMENDATION_SCORE) double recommendationScore,
                      @BsonProperty(Constants.REVIEW_STRING) String reviewString,
                      @BsonProperty(Constants.TIMESTAMP) Date timestamp
     ) {
        this.generalRate = generalRate;
        this.difficultyRate = difficultyRate;
        this.recommendationScore = recommendationScore;
        this.username = username;
        this.reviewString = reviewString;
        this.timestamp = timestamp;
    }

    /**
     * Regular constructor for creating a UserReview entity.
     *
     * This constructor automatically generates a time stamp for the entity.
     *
     * @param username  username
     * @param generalRate  generalRate
     * @param difficultyRate  difficultyRate
     * @param recommendationScore  recommendationScore
     * @param reviewString  reviewString
     */
    public UserReview( String username,
                       double generalRate,
                       double difficultyRate,
                       double recommendationScore,
                       String reviewString
    ) {
        this.generalRate = generalRate;
        this.difficultyRate = difficultyRate;
        this.recommendationScore = recommendationScore;
        this.username = username;
        this.reviewString = reviewString;
        this.timestamp = new Date();
    }

    /**
     * Customized equal comparison for UserReview entity.
     *
     * @param o object
     * @return true iff the objects are equal (same in this case).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReview that = (UserReview) o;
        return Double.compare(that.generalRate, generalRate) == 0 && Double.compare(that.difficultyRate, difficultyRate) == 0 && Double.compare(that.recommendationScore, recommendationScore) == 0 && username.equals(that.username) && reviewString.equals(that.reviewString) && timestamp.equals(that.timestamp);
    }

    /**
     * Customized hashcode for UserReview entity
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, generalRate, difficultyRate, recommendationScore, reviewString, timestamp);
    }

    public double getGeneralRate() {
        return generalRate;
    }

    public double getRecommendationScore() {
        return recommendationScore;
    }

    public double getDifficultyRate() {
        return difficultyRate;
    }

    public String getUsername() {
        return username;
    }

    public String getReviewString() {
        return reviewString;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setReviewString(String reviewString) {
        this.reviewString = reviewString;
    }


    public void setGeneralRate(double generalRate) {
        this.generalRate = generalRate;
    }

    public void setDifficultyRate(double difficultyRate) {
        this.difficultyRate = difficultyRate;
    }

    public void setRecommendationScore(double recommendationScore) {
        this.recommendationScore = recommendationScore;
    }
}
