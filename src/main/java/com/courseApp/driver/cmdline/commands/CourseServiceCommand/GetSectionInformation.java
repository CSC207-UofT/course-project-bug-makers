package com.courseApp.driver.cmdline.commands.CourseServiceCommand;

import com.courseApp.courseService.CourseServiceController;
import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class GetSectionInformation extends CourseCommand {
    public GetSectionInformation() {
        super(1, 1);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        StringBuilder result = new StringBuilder();
        CourseServiceController courseController = new CourseServiceController();
        for (String courseCodeWSection: arguments){
            // append all information about the section to result
            try {
                result.append(courseController.getSectionInformation(courseCodeWSection));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
