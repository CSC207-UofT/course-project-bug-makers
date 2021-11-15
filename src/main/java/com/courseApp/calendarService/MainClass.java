package com.courseApp.calendarService;

import com.courseApp.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainClass{
    public static void main(String[] args) throws IOException {
        JFrame jf = new JFrame("Demo");
        Container cp = jf.getContentPane();

        Map<String, Map<String, ArrayList<String>>> rawSchedule = new HashMap<>();
        Map<String, ArrayList<String>> course = new HashMap<>();
        ArrayList<String> array = new ArrayList<>();
        array.add("13:00-14:00");
        course.put("MO", array);
        rawSchedule.put("BIO230FLEC9901", course);

        DrawingCanvas tl = new DrawingCanvas(Constants.FALL_TERM, Constants.TYPE_WORKDAY, rawSchedule);
        cp.add(tl);
        jf.setSize(120*6, 60*32);
        jf.setVisible(true);
        tl.saveImage("Testing", "jpg", cp);
    }
}

