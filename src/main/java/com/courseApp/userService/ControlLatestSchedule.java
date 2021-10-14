package com.courseApp.userService;

import com.courseApp.entity.Schedule;

public interface ControlLatestSchedule {
    Schedule getLatestSchedule(String username);
}
