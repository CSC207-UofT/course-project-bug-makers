package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class UserRegister extends UserCommand{
    public UserRegister() {
        super(2, 2);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        return null;
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        return null;
    }
}
