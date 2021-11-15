package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class GetCourseList extends UserCommand{
    public GetCourseList() {
        super(0, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        checkArgumentsNum(arguments);
        String result = null;
        UserServiceController userServiceController = new UserServiceController();
        try{
            result = userServiceController.getCourseList(username);
        }catch(Throwable e) {
            e.printStackTrace();
        }
        if (result != null){return result;}
        else {return "Failed to get course list";}
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) {
        return null;
    }
}
