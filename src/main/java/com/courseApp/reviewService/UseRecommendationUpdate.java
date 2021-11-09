package com.courseApp.reviewService;

import java.util.Map;

public interface UseRecommendationUpdate {


    /**
     * generate a complex recommendation map by ratio in the recommendation map.
     *
     * The idea is to integrate the rates given by user and the recommendation score given by our model.
     *
     *
     * @param recommendationMap recommendation map, containing each instructor's name as key,
     *                          another map of recommendation score, general rate and difficulty rate as value.
     * @return complex recommendation map of instName to score.
     */
    Map<String, Double> generateComplexScoreMap(Map<String, Map<String, Double>> recommendationMap);
}
