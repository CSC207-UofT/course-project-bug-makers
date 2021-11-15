package com.courseApp.driver.cmdline.commands.CalendarServiceCommand;

import com.courseApp.calendarService.CalendarPresenter;
import com.courseApp.driver.cmdline.IShellState;
import com.courseApp.userService.UserServiceController;

import java.util.List;

public class PresentCalendar extends CalendarCommand {

    public PresentCalendar() {
        super(2, 2);
    }

    @Override
    public String executeCommand(IShellState shellState, List<String> arguments) throws Throwable {
        checkArgumentsNum(arguments);
        StringBuilder result = new StringBuilder();
        StringBuilder allErrorMsg = new StringBuilder();
        CalendarPresenter presenter = new CalendarPresenter();
        UserServiceController usc = new UserServiceController();
            // append all information about the course to result
        try {
            result.append(presenter.presentCalendar(arguments.get(0), arguments.get(1),
                    usc.getLatestSchedule(shellState.getUsername()).getScheduleMap()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
