package com.courseApp.driver.cmdline.commands.ReviewServiceCommand;

import com.courseApp.reviewService.ReviewServiceController;
import com.courseApp.driver.cmdline.IShellState;

import java.util.ArrayList;
import java.util.List;

public class GetInstReviewSummary extends ReviewCommand {

    public GetInstReviewSummary(){
        super(1, 1);
    }


    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        ReviewServiceController reviewController = new ReviewServiceController();
        StringBuilder result = new StringBuilder();
        for (String courseCode: arguments){
            try{
                result.append(reviewController.getInstReviewSummary(courseCode));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
