package com.courseApp.utils;

import java.util.List;

import com.courseApp.constants.Constants;

public class AggregateList {
    /**
     * Convert list to String.
     *
     * @param list list of calendar strings
     * @return the String format of calendar.
     */
    public static String aggregate(List<String> list){
        StringBuilder res = new StringBuilder();
        for(String i: list){
            res.append(i).append(Constants.CHANGE_LINE);
        }
        return res.toString();
    }
}
