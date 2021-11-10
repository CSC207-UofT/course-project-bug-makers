package com.courseApp.reviewService;

import com.courseApp.constants.Constants;


import java.util.HashMap;
import java.util.Map;

public class RecommendationRequestProcessor implements UseRecommendationUpdate, UseInferenceScore{
    /**
     * generate a complex recommendation map by ratio in the recommendation map.
     *
     * it computes the avg of recommendation score and general rate
     *
     * The idea is to integrate the rates given by user and the recommendation score given by our model.
     *
     * @param recommendationMap recommendation map, containing each instructor's name as key,
     *                          another map of recommendation score, general rate and difficulty rate as value.
     * @return complex recommendation map of instName to score.
     */
    @Override
    public Map<String, Double> generateComplexScoreMap(Map<String, Map<String, Double>> recommendationMap) {
        Map<String, Double> res = new HashMap<>();
        for(var entry : recommendationMap.entrySet()){
            Map<String, Double> instMap = entry.getValue();
            Double generalRate = instMap.get(Constants.GENERAL_RATE);
            Double recommendationScore = instMap.get(Constants.RECOMMENDATION_SCORE);
            res.put(entry.getKey(), (generalRate + recommendationScore)/2);
        }
        return res;

    }

    /**
     * Run the model inference on the review string and get the recommendation score.
     *
     * @param reviewString user review string
     * @return text recommendation score in double format
     */
    @Override
    public double modelInference(String reviewString) {
//        // Initialization
//        BertNLClassifier classifier = BertNLClassifier.createFromFile(context, modelFile);
//
//        // Run inference
//        List<Category> results = classifier.classify(input);
        return 5.0D;
    }
}
