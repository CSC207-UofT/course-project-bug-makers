package com.courseApp.driver.cmdline.commands.UserServiceCommand;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class RmCourse extends UserCommand {
    public RmCourse() {
        super(100, 1);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        checkArgumentsNum(arguments);
        boolean result = false;
        UserServiceController userServiceController = new UserServiceController();
        for (String courseCode : arguments) {
            try {
                result = userServiceController.rmCourse(username, courseCode);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        if (result){return "Remove course successfully";}
        else {return "Failed to remove course";}

    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) {
        return null;
    }
}
