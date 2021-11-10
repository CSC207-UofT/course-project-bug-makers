package com.courseApp.courseService;

/**
 * Interface for providing user course planning function.
 */
public interface ControlUserCoursePlanning {

    /**
     * Plan the schedule for user with given username and password.
     * Course planning will base on courseList, then wishList with courseList at a higher priority
     * and wishList at a lower priority.
     *
     * Planned schedule should be added to scheduleList iff the planning is not successful.
     *
     * Return the planned schedule list String iff the planning is successful, otherwise, null.
     *
     * @param username username
     * @return schedule list String
     */
    String planCourse(String username);
}
