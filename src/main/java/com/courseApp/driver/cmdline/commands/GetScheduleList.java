package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class GetScheduleList extends UserCommand{
    public GetScheduleList() {
        super(0, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        checkArgumentsNum(arguments);
        String result = null;
        UserServiceController userServiceController = new UserServiceController();
        try{
            result = userServiceController.getScheduleList(username);
        }catch(Throwable e) {
            e.printStackTrace();
        }
        if (result != null){return result;}
        else {return "Failed to get schedule list";}
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        return null;
    }

}