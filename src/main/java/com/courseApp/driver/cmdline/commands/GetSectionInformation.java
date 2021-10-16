package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class GetSectionInformation extends CourseCommand{
    public GetSectionInformation() {
        super(1, 1);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        return null;
    }
}
