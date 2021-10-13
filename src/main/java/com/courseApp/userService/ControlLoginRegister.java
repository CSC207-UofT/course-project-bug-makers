package com.courseApp.userService;

public interface ControlLoginRegister {

    boolean userLogin(String username, String password);

    boolean userRegister(String username, String password);
}
