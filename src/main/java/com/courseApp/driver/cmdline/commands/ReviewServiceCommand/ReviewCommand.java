package com.courseApp.driver.cmdline.commands.ReviewServiceCommand;

import com.courseApp.driver.cmdline.commands.Command;

public abstract class ReviewCommand extends Command {
    public ReviewCommand(int maxArguments, int minArguments) {
        super(maxArguments, minArguments);
    }
}
