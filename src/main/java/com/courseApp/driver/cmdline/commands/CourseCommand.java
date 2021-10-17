package com.courseApp.driver.cmdline.commands;

public abstract class CourseCommand extends Command{
    public CourseCommand(int maxArguments, int minArguments) {
        super(maxArguments, minArguments);
    }
}
