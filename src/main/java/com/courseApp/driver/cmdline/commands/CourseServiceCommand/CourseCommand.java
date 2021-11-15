package com.courseApp.driver.cmdline.commands.CourseServiceCommand;

import com.courseApp.driver.cmdline.commands.Command;

public abstract class CourseCommand extends Command {
    public CourseCommand(int maxArguments, int minArguments) {
        super(maxArguments, minArguments);
    }
}
