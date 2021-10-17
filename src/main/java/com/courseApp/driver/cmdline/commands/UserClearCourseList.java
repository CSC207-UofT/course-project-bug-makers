package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class UserClearCourseList extends UserCommand{

    public UserClearCourseList() {
        super(0, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        checkArgumentsNum(arguments);
        boolean result = false;
        UserServiceController userServiceController = new UserServiceController();
        try{
            result = userServiceController.userClearCourseList(username);
        }catch(Throwable e) {
            e.printStackTrace();
        }
        if (result){return "Clear course list successfully";}
        else {return "Failed to clear course list";}
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        return null;
    }
}