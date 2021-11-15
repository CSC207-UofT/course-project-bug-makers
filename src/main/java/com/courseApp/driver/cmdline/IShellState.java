package com.courseApp.driver.cmdline;

import java.util.List;

public interface IShellState {
    boolean isRunning();

    void stopRunning();

    void addHistory(String inputLine);

    void clearHistory();

    List<String> getHistory();

    boolean isUserService(String inputLine);

    boolean isCourseService(String inputLine);

    boolean isCalendarService(String inputLine);

    String getUsername();


}