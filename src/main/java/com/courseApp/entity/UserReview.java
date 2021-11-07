package com.courseApp.entity;

import java.sql.Timestamp;

/**
 * User Review entity for username, general rate, difficulty rate, timestamp and review.
 */
public class UserReview {
     private final String username;
     private final float generalRate;
     private final float difficultyRate;
     private final float recommendationScore;
     private final String reviewString;
     private final Timestamp timestamp;

    public UserReview(String username,float generalRate, float difficultyRate, float recommendationScore, String reviewString) {
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
}
