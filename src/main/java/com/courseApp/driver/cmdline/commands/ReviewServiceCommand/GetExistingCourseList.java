package com.courseApp.driver.cmdline.commands.ReviewServiceCommand;

import com.courseApp.reviewService.ReviewServiceController;
import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class GetExistingCourseList extends ReviewCommand {

    public GetExistingCourseList(){
        super(0, 1);
    }


    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        ReviewServiceController reviewController = new ReviewServiceController();
        StringBuilder result = new StringBuilder();

        try{
            result.append(reviewController.getExistingCourseList());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
