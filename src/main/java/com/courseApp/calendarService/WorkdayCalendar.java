package com.courseApp.calendarService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.courseApp.constants.Constants;

public class WorkdayCalendar extends CalendarFactory implements Presentable {

    public WorkdayCalendar(Map map) {
        super(map);
    }

    public int getWeekDay(String day) {
        // see with draw
        return switch (day) {
            case "MO" -> 14;
            case "TU" -> 27;
            case "WE" -> 40;
            case "TH" -> 53;
            default -> 66;
        };
    }

    @Override
    public String present(){
        List<String> list = Constants.getListedWorkday();
        for(Object courseCode : this.course.keySet()){
            HashMap lectures = (HashMap) this.course.get(courseCode);
            for (Object weekday : lectures.keySet()){
                List<String> time = (List) lectures.get(weekday);
                int r = Constants.START_HEIGHT +
                        ((super.getIntTime(time.get(0)) - Constants.START_TIME)*Constants.HOUR_HEIGHT);
                String row = new String(list.get(r)); //get the row of start time
                String row2 = new String(list.get(r+1));
                int w = getWeekDay((String) weekday);
                list.set(r, row.substring(0,w + 3)+ (String)((String) courseCode).substring(0,7) +
                        row.substring(w + Constants.CELL_FILLING -2, row.length()));
                list.set(r+1, row2.substring(0,w + 3)+ (String)((String) courseCode).substring(7, ((String) courseCode).length()) +
                        row2.substring(w + Constants.CELL_FILLING -2, row2.length()));
            }
    }
        StringBuilder res = new StringBuilder("");
        for(String i: list){
            res.append(i).append("\n");
        }
       return res.toString();
    }

    public static void main(String[] args) {
        ArrayList<String> th_schedule = new ArrayList<>();
        th_schedule.add("17:00");
        th_schedule.add("18:00");


        ArrayList<String> tu_schedule = new ArrayList<>();
        tu_schedule.add("11:00");
        tu_schedule.add("12:00");


        ArrayList<String> th_schedule2 = new ArrayList<>();
        th_schedule2.add("8:00");
        th_schedule2.add("9:00");


        ArrayList<String> mo_schedule2 = new ArrayList<>();
        mo_schedule2.add("10:00");
        mo_schedule2.add("11:00");

        Map<String, ArrayList<String>> day = new HashMap<>();
        day.put("TH", th_schedule);
        day.put("TU", tu_schedule);

        Map<String, ArrayList<String>> day2 = new HashMap<>();
        day2.put("TH", th_schedule2);
        day2.put("MO", mo_schedule2);

        Map<String, Map<String, ArrayList<String>>> cad = new HashMap<>();
        cad.put("CSC207SLEC0101", day);
        cad.put("CSC108SLEC0102", day2);

        System.out.println(cad);

        System.out.println(new WorkdayCalendar(cad).present());

    }


}

