package com.courseApp.driver.cmdline.commands;

import com.courseApp.calendarService.CalendarPresenter;
import com.courseApp.driver.cmdline.IShellState;

import java.util.List;

public class PresentCalendar extends CalendarCommand{

    public PresentCalendar(int maxArguments, int minArguments) {
        super(1, 1);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        StringBuilder result = new StringBuilder();
        StringBuilder allErrorMsg = new StringBuilder();
        CalendarPresenter presenter = new CalendarPresenter();
        for (String courseCode: arguments){
            // append all information about the course to result
            try {
                result.append(presenter.presentCalendar());
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return result.toString();
        return null;
    }
}
