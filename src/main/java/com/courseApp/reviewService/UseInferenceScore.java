package com.courseApp.reviewService;

public interface UseInferenceScore {
    /**
     * Run the model inference on the review string and get the recommendation score.
     *
     * @param reviewString user review string
     *
     * @throws Exception exception
     * @return text recommendation score in double format
     */
    double modelInference(String reviewString) throws Exception;
}
