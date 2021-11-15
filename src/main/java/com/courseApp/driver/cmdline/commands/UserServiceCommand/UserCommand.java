package com.courseApp.driver.cmdline.commands.UserServiceCommand;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.driver.cmdline.commands.Command;

import java.util.List;

public abstract class UserCommand extends Command {

    public UserCommand(int maxArguments, int minArguments) {
        super(maxArguments, minArguments);
    }

    abstract public String executeCommand
            (IShellState shellState, List<String> arguments, String username) throws Throwable;
}
