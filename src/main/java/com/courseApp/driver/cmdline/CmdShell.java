package com.courseApp.driver.cmdline;

import com.courseApp.driver.cmdline.commands.CommandExecutor;
import com.courseApp.userService.UserServiceController;
import java.util.Scanner;
import com.courseApp.constants.Constants;

public class CmdShell {

    public static void run(){
        Scanner in = new Scanner(System.in);
        System.out.println(Constants.WELCOME_LOGO);
        System.out.println("Welcome to MyCoursePlanner");
        System.out.println("Do you have an account? Y/N");
        String haveAccount = in.nextLine();
        String username = "";

        username = loginOrRegister(in, haveAccount, username);

        IShellState shellState = new ShellState(username);
        CommandExecutor commandExecutor = new CommandExecutor();
        while (shellState.isRunning()) {
            System.out.println("Enter command (type ?commands for the list of commands): ");
            String commandLine = in.nextLine();
            if (commandLine.equals("?commands")){
                System.out.println("User commands: " + Constants.USER_COMMAND_DIC.keySet());
                System.out.println("Course commands: " + Constants.COURSE_COMMAND_DIC.keySet());
                System.out.println("Calendar commands: " + Constants.CALENDAR_COMMAND_DIC.keySet());
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
        if (haveAccount.equals("Y")) {
            boolean exitLoop = false;
            while (!exitLoop) {
                System.out.println("Please enter your username: ");
                String inputUsername = in.nextLine();
                System.out.println("Please enter your password: ");
                String inputPassword = in.nextLine();
                UserServiceController userServiceController = new UserServiceController();
                if (userServiceController.userLogin(inputUsername, inputPassword)) {
                    System.out.println("Login Success!");
                    username = inputUsername;
                    exitLoop = true;
                } else { //if username or password does not match our database
                    System.out.println("Login Failed :( Try again or register a new account. TryAgain/ Register ");
                    if (in.nextLine().equals("Register")) {
                        wantToRegister = true;
                        exitLoop = true;
                    }
                }
            }
        }
        if (haveAccount.equals("N") || wantToRegister) {
            System.out.println("Let's create an account for you! \n Please enter your username: ");
            String inputUsername = in.nextLine();
            System.out.println("Please enter your password: ");
            String inputPassword = in.nextLine();
            UserServiceController userServiceController = new UserServiceController();
            userServiceController.userRegister(inputUsername, inputPassword);
            username = inputUsername;
        }
        return username;
    }
}
