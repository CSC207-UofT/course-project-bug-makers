package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class UserLogin extends UserCommand{

    public UserLogin() {

        super(2, 2);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Exception {
        return null;
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        return null;
    }
}
