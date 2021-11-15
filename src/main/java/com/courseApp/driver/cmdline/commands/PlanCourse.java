package com.courseApp.driver.cmdline.commands;


import com.courseApp.courseService.CourseServiceController;
import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class PlanCourse extends CourseCommand{

    public PlanCourse() {
        super(0, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        StringBuilder result = new StringBuilder();
        StringBuilder allErrorMsg = new StringBuilder();
        UserServiceController usc = new UserServiceController();
        CourseServiceController csc = new CourseServiceController();
        // append all information about the course to result
        try {
            String res = csc.planCourse(shellState.getUsername());
            if(res != null){result.append(res);}else {result.append("Fail to schedule.");}
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
