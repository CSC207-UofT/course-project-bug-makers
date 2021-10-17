package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.entity.Schedule;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class GetLatestSchedule extends UserCommand{
    public GetLatestSchedule() {
        super(0, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        checkArgumentsNum(arguments);
        Schedule result = null;
        UserServiceController userServiceController = new UserServiceController();
        try{
            result = userServiceController.getLatestSchedule(username);
        }catch(Throwable e) {
            e.printStackTrace();
        }
        if (result != null){return result.toString();}
        else {return "Failed to get latest schedule";}
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        return null;
    }
}
