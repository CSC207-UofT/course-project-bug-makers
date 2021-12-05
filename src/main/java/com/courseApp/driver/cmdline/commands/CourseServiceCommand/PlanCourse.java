package com.courseApp.driver.cmdline.commands.CourseServiceCommand;


import com.courseApp.courseService.CourseServiceController;
import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class PlanCourse extends CourseCommand {

    public PlanCourse() {
        super(0, 0);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        Scanner in = new Scanner(System.in);
        int index = 0;
        checkArgumentsNum(arguments);
        StringBuilder result = new StringBuilder();
        StringBuilder allErrorMsg = new StringBuilder();
        UserServiceController usc = new UserServiceController();
        CourseServiceController csc = new CourseServiceController();
        // append all information about the course to result
        try {
            String res = csc.planCourse(shellState.getUsername(), index);
            if(res != null){result.append(res);}else {result.append("Fail to schedule.");}
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println(result);
        System.out.println("See next schedule? Y/N");
        while (!in.nextLine().equals("N")) {
            result = new StringBuilder();
            index += 1;
            try {result.append(csc.planCourse(shellState.getUsername(), index));
            } catch (Throwable IndexOutOfBoundsException) {
                index = 0;
                result.append(csc.planCourse(shellState.getUsername(), index));
            }
            System.out.println(result);
            System.out.println("See next schedule? Y/N");
        }
        return result.toString();
    }
}
