package com.courseApp.driver.cmdline.commands;

import com.courseApp.constants.Exceptions;
import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.utils.CmdArgTuple;
import com.courseApp.constants.Constants;

import java.util.List;

public class CommandExecutor {

    /**
     * Executes the provided command line accordingly
     *
     * @param shellState  is the state of the program
     * @param commandLine is the given command line
     * @return string that is given back as command is executed
     * @throws Exception if any of the provided arguments is invalid
     */
    public String executeCommand(IShellState shellState, String commandLine) throws Throwable {

        shellState.addHistory(commandLine);

        // use command reader to separate command with arguments
        CmdArgTuple cmdArgTuple = CommandReader.parseCommandLine(commandLine); // (command, arguments)
        String command = cmdArgTuple.getCommand();
        if (!CommandReader.isValidCommand(command)) {
            throw new Exception(Exceptions.COMMAND_NOT_FOUND);
        }
        List<String> arguments = cmdArgTuple.getArguments();
        Command apprCmdObject;
        if (Constants.USER_COMMAND_DIC.containsKey(command)) {
            apprCmdObject = Constants.USER_COMMAND_DIC.get(command);
        } else if (Constants.COURSE_COMMAND_DIC.containsKey(command)) {
            apprCmdObject = Constants.COURSE_COMMAND_DIC.get(command);
        } else {
            apprCmdObject = Constants.CALENDAR_COMMAND_DIC.get(command);
        }

        // return the result of the executed command
        String result;
        result = apprCmdObject.executeCommand(shellState, arguments);

        return result;
    }
}