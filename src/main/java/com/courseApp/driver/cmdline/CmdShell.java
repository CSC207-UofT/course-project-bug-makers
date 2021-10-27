package com.courseApp.driver.cmdline;

import com.courseApp.driver.cmdline.commands.CommandExecutor;
import com.courseApp.userService.UserServiceController;
import java.util.Scanner;
import com.courseApp.constants.Constants;

public class CmdShell {

    public static void run(){
        Scanner in = new Scanner(System.in);
        System.out.println(Constants.WELCOME_LOGO);
        System.out.println(Constants.CMD_WELCOME);
        System.out.println(Constants.CMD_CHECK_ACCOUNT);
        String haveAccount = in.nextLine();
        String username = "";

        username = loginOrRegister(in, haveAccount, username);

        IShellState shellState = new ShellState(username);
        CommandExecutor commandExecutor = new CommandExecutor();
        while (shellState.isRunning()) {
            System.out.println(Constants.CMD_ENTER);
            String commandLine = in.nextLine();
            if (commandLine.equals(Constants.HELPER)){
                System.out.println(Constants.HELPER_USER + Constants.USER_COMMAND_DIC.keySet());
                System.out.println(Constants.HELPER_COURSE + Constants.COURSE_COMMAND_DIC.keySet());
                System.out.println(Constants.HELPER_CALENDAR + Constants.CALENDAR_COMMAND_DIC.keySet());
            } else {
                try {
                    String output = commandExecutor.executeCommand(shellState, commandLine, username);
                    System.out.println(output);
                } catch (Throwable throwable) {
                    System.out.println(throwable.getMessage());
                }
            }
        }

        in.close();

    }


    // helper method
    private static String loginOrRegister(Scanner in, String haveAccount, String username) {
        boolean wantToRegister = false;
        if (haveAccount.equals(Constants.YES)) {
            boolean exitLoop = false;
            while (!exitLoop) {
                System.out.println(Constants.ENTER_USERNAME);
                String inputUsername = in.nextLine();
                System.out.println(Constants.ENTER_PASSWORD);
                String inputPassword = in.nextLine();
                UserServiceController userServiceController = new UserServiceController();
                if (userServiceController.userLogin(inputUsername, inputPassword)) {
                    System.out.println(Constants.SUCCESS);
                    username = inputUsername;
                    exitLoop = true;
                } else { //if username or password does not match our database
                    System.out.println(Constants.FAIL);
                    if (in.nextLine().equals(Constants.REGISTER)) {
                        wantToRegister = true;
                        exitLoop = true;
                    }
                }
            }
        }
        if (haveAccount.equals(Constants.NO) || wantToRegister) {
            System.out.println(Constants.CREATE_ACCOUNT);
            String inputUsername = in.nextLine();
            System.out.println(Constants.CREATE_PASSWORD);
            String inputPassword = in.nextLine();
            UserServiceController userServiceController = new UserServiceController();
            userServiceController.userRegister(inputUsername, inputPassword);
            username = inputUsername;
        }
        return username;
    }
}
