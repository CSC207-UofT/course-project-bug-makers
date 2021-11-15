package com.courseApp.driver.cmdline.commands.ReviewServiceCommand;

import com.courseApp.reviewService.ReviewServiceController;
import com.courseApp.driver.cmdline.IShellState;


import java.util.List;

public class DeleteUserReview extends ReviewCommand {

    public DeleteUserReview(){
        super(3, 3);
    }


    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        ReviewServiceController reviewController = new ReviewServiceController();
        boolean result = false;
        String courseCode = arguments.get(0);
        String instName = arguments.get(1);
        String userName = arguments.get(2);


        try{
            result = reviewController.deleteUserReview(
                    courseCode, instName, userName);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if (result){return "Deleted user review successfully";}
        else {return "Failed to delete user review";}
    }
}
