package com.courseApp.userService;

public interface ControlLoginRegister {

    /**
     *  User login
     *
     * @param username username
     * @param password password
     * @return true iff login is successful
     */
    boolean userLogin(String username, String password);

    /**
     * User register
     *
     * @param username username
     * @param password password
     * @return true iff register is successful
     */
    boolean userRegister(String username, String password);
}
