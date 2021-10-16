package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public abstract class UserCommand extends Command{
    protected final String username;
    public UserCommand(int maxArguments, int minArguments) {
        super(maxArguments, minArguments);
        this.username = username;
    }




}
