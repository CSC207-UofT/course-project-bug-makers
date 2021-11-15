package com.courseApp.driver.cmdline.commands.CalendarServiceCommand;

import com.courseApp.driver.cmdline.commands.Command;

public abstract class CalendarCommand extends Command {
    public CalendarCommand(int maxArguments, int minArguments) {
        super(maxArguments, minArguments);
    }
}
