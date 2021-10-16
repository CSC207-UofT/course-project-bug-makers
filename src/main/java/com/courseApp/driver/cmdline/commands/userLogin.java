package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class userLogin extends UserCommand{

    public userLogin() {

        super(2, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Exception {
        return null;
    }
}
