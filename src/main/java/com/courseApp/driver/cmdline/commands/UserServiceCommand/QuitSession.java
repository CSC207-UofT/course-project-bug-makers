package com.courseApp.driver.cmdline.commands.UserServiceCommand;

import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class QuitSession extends UserCommand {
    public QuitSession() {
        super(0, 0);
    }


    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) {
        shellState.stopRunning();
        return "Goodbye " + username + ". Thank you for using myCourseApp!";
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) {
        return null;
    }
}
