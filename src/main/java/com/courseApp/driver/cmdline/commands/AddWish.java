package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class AddWish extends UserCommand{
    public AddWish() {
        super(100, 1);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        checkArgumentsNum(arguments);
        boolean result = false;
        UserServiceController userServiceController = new UserServiceController();
        for (String courseCode : arguments) {
            try {
                result = userServiceController.addWish(username, courseCode);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        if (result){return "Added course to wish list successfully";}
        else {return "Failed to add course to wish list";}
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) {
        return null;
    }
}
