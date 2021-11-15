package com.courseApp.driver.cmdline.commands.ReviewServiceCommand;

import com.courseApp.reviewService.ReviewServiceController;
import com.courseApp.driver.cmdline.IShellState;

import java.util.ArrayList;
import java.util.List;

public class GetUserReviewSummary extends ReviewCommand {

    public GetUserReviewSummary(){
        super(2, 2);
    }


    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        ReviewServiceController reviewController = new ReviewServiceController();
        StringBuilder result = new StringBuilder();
        String courseCode = arguments.get(0);
        String instName = arguments.get(1);

        try{
            result.append(reviewController.getUserReviewSummary(courseCode, instName));
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
