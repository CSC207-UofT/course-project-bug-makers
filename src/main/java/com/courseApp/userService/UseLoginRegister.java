package com.courseApp.userService;


/**
 * Interface for realizing user login/register service.
 */
public interface UseLoginRegister {

    /**
     * User Login Authentication.
     *
     * @return True iff the username and password are consistent with the data in the database.
     */
    boolean userLogin();

    /**
     * User register service. It will check if the username already exists.
     *
     * @return True iff register is successful
     */
    boolean userRegister();
}
