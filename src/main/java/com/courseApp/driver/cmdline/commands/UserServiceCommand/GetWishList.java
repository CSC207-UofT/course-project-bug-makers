package com.courseApp.driver.cmdline.commands.UserServiceCommand;

import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class GetWishList extends UserCommand {
    public GetWishList() {
        super(0, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments, String username) throws Throwable {
        checkArgumentsNum(arguments);
        String result = null;
        UserServiceController userServiceController = new UserServiceController();
        try{
            result = userServiceController.getWishList(username);
        }catch(Throwable e) {
            e.printStackTrace();
        }
        if (result != null){return result;}
        else {return "Failed to get wish list";}
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) {
        return null;
    }

}
