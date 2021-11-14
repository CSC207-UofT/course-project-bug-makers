package com.courseApp.userService;

import com.courseApp.entity.Schedule;

/**
 * Interface for generating the schedule.
 */
public interface ControlLatestSchedule {
    /**
     * Return the default schedule
     *
     * @param username username
     * @return Schedule at the default position
     */
    Schedule getLatestSchedule(String username);
}