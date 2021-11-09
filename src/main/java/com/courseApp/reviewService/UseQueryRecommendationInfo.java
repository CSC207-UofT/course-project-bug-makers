package com.courseApp.reviewService;

import java.util.Map;

public interface UseQueryRecommendationInfo {

    /**
     * Get the recommendation map for a course, where the map should be:
     *  String instName:
     *                      - generalRate: Double
     *                      - difficultyRate: Double
     *                      - recommendationScore : Double
     *
     *
     * @param courseCode course code
     * @return recommendation map
     */
    Map<String, Map<String, Double>> getRecommendationMap(String courseCode);
}
