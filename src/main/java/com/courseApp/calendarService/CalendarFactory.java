package com.courseApp.calendarService;

import java.util.HashMap;
import java.util.Map;

public abstract class CalendarFactory {
    protected Map course;
    public CalendarFactory(Map map) {
        this.course = map;
    }

    public int getIntTime(String s){
        return Integer.parseInt(s.substring(0,2));
    }


}
