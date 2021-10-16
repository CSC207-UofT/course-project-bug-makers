package com.courseApp.driver.cmdline;

import java.util.ArrayList;
import java.util.List;

public class ShellState implements IShellState{

    boolean running;
    boolean userService;
    boolean courseService;
    boolean calendarService;
    private final List<String> commandsHistory;
    String username;

    public ShellState(String username){
        this.running = true;
        this.userService = false;
        this.courseService = false;
        this.calendarService = false;
        this.username = username;
        this.commandsHistory = new ArrayList<String>();

    }

    public boolean isRunning(){
        return running;
    }

    public void stopRunning(){
        running = false;
    }

    public void addHistory(String inputLine){
        commandsHistory.add(inputLine);
    }

    public void clearHistory(){
        commandsHistory.clear();
    }

    public List<String> getHistory(){
        return commandsHistory;
    }

    public boolean isUserService(String inputLine){
        return userService;
    }

    public boolean isCourseService(String inputLine){
        return courseService;
    }

    public boolean isCalendarService(String inputLine){
        return calendarService;
    }

    public String getUsername(){
        return username;
    }
}
