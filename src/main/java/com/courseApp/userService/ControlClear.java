package com.courseApp.userService;

public interface ControlClear {

    /**
     * Clear the user's course list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @return true iff clear is successful
     */
    boolean userClearCourseList(String username);

    /**
     * Clear the user's wish list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @return true iff clear is successful
     */
    boolean userClearWishList(String username);

    /**
     * Clear the user's schedule list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @return true iff clear is successful
     */
    boolean userClearScheduleList(String username);
}
