package com.courseApp.reviewService;

import java.util.ArrayList;

public interface ControlRecommendationRank {

    /**
     * Get the arraylist of ranked instructor names for targeted course.
     *
     * The arraylist should be in decreasing order of recommendation index, which the first inst in the list should be
     * the most recommended and the last one should be least recommended.
     *
     * @param courseCode given course code
     * @return array list of instructor name.
     */
    ArrayList<String> getInstRank(String courseCode);
}
