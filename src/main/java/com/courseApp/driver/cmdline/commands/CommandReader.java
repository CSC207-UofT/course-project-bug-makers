package com.courseApp.driver.cmdline.commands;

import com.courseApp.constants.Constants;
import com.courseApp.constants.Exceptions;
import com.courseApp.utils.CmdArgTuple;

import java.util.Arrays;
import java.util.List;

/**
 * Parses command line input, recognizes a string surrounded by quotes as one
 * argument
 */
public class CommandReader {

    /**
     * Parses the input into a tuple that holds command as String and arguments
     * as List of Strings
     * @param commandLine the input command line
     * @throws Exception exception
     * @return CmdArgTuple the tuple that holds command and its arguments
     */
    public static CmdArgTuple parseCommandLine(String commandLine) throws Exception{
        // split up the command line by the spaces
        List<String> split = Arrays.asList(commandLine.split(" "));
        if (split.size() == 0) {
            throw new Exception(Exceptions.COMMAND_NOT_FOUND);
        }
        String command = split.get(0);
        List<String> arguments = split.subList(1, split.size());
        return new CmdArgTuple(command, arguments);
    }

    /**
     * This method checks if the given command is valid
     * @param command the input command
     * @return boolean that indicates if command is valid
     */
    public static boolean isValidCommand(String command) {
        return Constants.USER_COMMAND_DIC.containsKey(command) ||
                Constants.COURSE_COMMAND_DIC.containsKey(command) ||
                Constants.REVIEW_COMMAND_DIC.containsKey(command) ||
                Constants.CALENDAR_COMMAND_DIC.containsKey(command);
    }

}
