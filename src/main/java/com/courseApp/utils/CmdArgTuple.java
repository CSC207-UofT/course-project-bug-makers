package com.courseApp.utils;

import java.util.List;

/**
 * This is a class that mimics a tuple in python
 */
public class CmdArgTuple {
    private final String command;
    private final List<String> arguments;

    public CmdArgTuple(String command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Return first object
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Return second object
     */
    public List<String> getArguments() {
        return this.arguments;
    }
}
