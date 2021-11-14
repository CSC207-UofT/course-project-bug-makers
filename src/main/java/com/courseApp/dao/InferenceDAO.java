package com.courseApp.dao;

import java.io.IOException;

/**
 * Interface for providing interaction with inference server.
 */
public interface InferenceDAO {

    /**
     * Take in a review string and send it to the inference server and return the result.
     *
     * @param reviewString review string for user
     * @return inference result of review string (convert to double)
     */
    double modelInference(String reviewString) throws Exception;
}
