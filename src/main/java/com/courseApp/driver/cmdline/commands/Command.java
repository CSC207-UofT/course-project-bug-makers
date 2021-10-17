package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

/**
 * This is a base class for any commands that might be needed in the program
 */
public abstract class Command {
    protected final int maxArguments;
    protected final int minArguments;

    public Command(int maxArguments, int minArguments) {
        this.maxArguments = maxArguments;
        this.minArguments = minArguments;
    }

    public int getMaxArg() {
        return maxArguments;
    }

    public int getMinArg() {
        return minArguments;
    }

    abstract public String executeCommand
            (IShellState shellState, List<String> arguments) throws Throwable;


    protected void checkArgumentsNum(List<String> arguments) throws Exception {
        if (arguments.size() > maxArguments) {
            throw new Exception("too many arguments provided");
        }
        if (arguments.size() < minArguments) {
            throw new Exception("too little arguments provided");
        }
    }

}
