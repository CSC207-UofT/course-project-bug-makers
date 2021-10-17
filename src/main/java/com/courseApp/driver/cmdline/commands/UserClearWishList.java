package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class UserClearWishList extends UserCommand {
    public UserClearWishList() {
        super(0, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        checkArgumentsNum(arguments);
        boolean result = false;
        UserServiceController userServiceController = new UserServiceController();
        try{
            result = userServiceController.userClearWishList(username);
        }catch(Throwable e) {
            e.printStackTrace();
        }
        if (result){return "Clear wish list successfully";}
        else {return "Failed to clear wish list course";}
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        return null;
    }
}

