package com.courseApp.driver.cmdline.commands.ReviewServiceCommand;

import com.courseApp.reviewService.ReviewServiceController;
import com.courseApp.driver.cmdline.IShellState;

import java.util.ArrayList;
import java.util.List;

public class GetInstRank extends ReviewCommand {

    public GetInstRank(){
        super(1, 1);
    }


    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        ReviewServiceController reviewController = new ReviewServiceController();
        StringBuilder result = new StringBuilder();
        String courseCode = arguments.get(0);

        try{
            result.append(reviewController.getInstRank(courseCode));
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
