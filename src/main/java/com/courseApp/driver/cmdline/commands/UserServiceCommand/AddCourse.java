package com.courseApp.driver.cmdline.commands.UserServiceCommand;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class AddCourse extends UserCommand {
    public AddCourse() {
        super(100, 1);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        checkArgumentsNum(arguments);
        boolean result = false;
        UserServiceController userServiceController = new UserServiceController();
        for (String courseCode : arguments) {
            try {
                result = userServiceController.addCourse(username, courseCode);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        if (result){return "Added course successfully";}
        else {return "Failed to add course";}
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) {
        return null;
    }
}
