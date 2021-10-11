package com.courseApp.calendarService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class CalendarFactory implements TimeConversion {
    protected Map course;
    public CalendarFactory(Map map) {
        this.course = map;
    }

    @Override
    public int getIntTime(String s){
        return Integer.parseInt(s.split(":")[0]);
    }
}
