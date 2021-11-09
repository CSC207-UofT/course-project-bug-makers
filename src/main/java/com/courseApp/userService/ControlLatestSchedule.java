package com.courseApp.userService;

import com.courseApp.entity.Schedule;

public interface ControlLatestSchedule {
    /**
     * Return the default schedule
     *
     * @param username username
     * @return Schedule at the default position
     */
    Schedule getLatestSchedule(String username);
}