package com.courseApp.courseService;

import com.courseApp.entity.Schedule;

/**
 * Interface for realizing schedule map generation.
 */
public interface UseScheduleInfo {
    /**
     * Update schedule map by given Schedule entity based on schedule entity's section list.
     *
     * @param schedule updated Schedule entity.
     */
    void updateScheduleMap(Schedule schedule);
}
