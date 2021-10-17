/**
package com.courseApp.driver.cmdline.commands;

import com.courseApp.courseService.CourseServiceController;
import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.entity.Schedule;

import java.util.List;

public class GetScheduleSummary extends CourseCommand{

    public GetScheduleSummary(int maxArguments, int minArguments) {
        super(1, 1);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        StringBuilder result = new StringBuilder();
        StringBuilder allErrorMsg = new StringBuilder();
        CourseServiceController courseController = new CourseServiceController();
        for (String schedule: arguments){
            // append all information about the Schedule to result
            try {
                result.append(courseController.getScheduleSummary(schedule));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
 */