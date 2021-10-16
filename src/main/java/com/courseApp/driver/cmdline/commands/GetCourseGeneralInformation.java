package com.courseApp.driver.cmdline.commands;

import com.courseApp.courseService.CourseServiceController;
import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class GetCourseGeneralInformation extends CourseCommand{

    public GetCourseGeneralInformation() {
        super(1, 1);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable{
        checkArgumentsNum(arguments);
        StringBuilder result = new StringBuilder();
        StringBuilder allErrorMsg = new StringBuilder();
        CourseServiceController courseController = new CourseServiceController();
        for (String courseCode: arguments){
            // append all information about the course to result
            try {
                result.append(courseController.getCourseGeneralInformation(courseCode));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
