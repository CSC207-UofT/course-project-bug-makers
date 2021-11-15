package com.courseApp.driver.cmdline.commands.ReviewServiceCommand;

import com.courseApp.reviewService.ReviewServiceController;
import com.courseApp.driver.cmdline.IShellState;

import java.util.ArrayList;
import java.util.List;

public class CreateNewCourse extends ReviewCommand {

    public CreateNewCourse(){
        super(1, 1);
    }


    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        ReviewServiceController reviewController = new ReviewServiceController();
        boolean result = false;
        for (String courseCode: arguments){
            try{
                result = reviewController.createNewCourse(courseCode);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        if (result){return "Added new course review successfully";}
        else {return "Failed to add course review";}
    }
}
