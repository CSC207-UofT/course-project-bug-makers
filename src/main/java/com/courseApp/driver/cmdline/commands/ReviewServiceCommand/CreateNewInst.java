package com.courseApp.driver.cmdline.commands.ReviewServiceCommand;

import com.courseApp.reviewService.ReviewServiceController;
import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class CreateNewInst extends ReviewCommand {

    public CreateNewInst(){
        super(2, 2);
    }


    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        ReviewServiceController reviewController = new ReviewServiceController();
        boolean result = false;
        String courseCode = arguments.get(0);
        String instName = arguments.get(1);

        try{
            result = reviewController.createNewInst(courseCode, instName);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if (result){return "Added new instructor review successfully";}
        else {return "Failed to add instructor review, please check the instructor name";}
    }
}
