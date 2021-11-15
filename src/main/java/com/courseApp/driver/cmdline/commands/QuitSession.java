package com.courseApp.driver.cmdline.commands;

import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class QuitSession extends Command{
    public QuitSession() {
        super(0, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) {
        shellState.stopRunning();
        return null;
    }
}
