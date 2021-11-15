package com.courseApp.calendarService;

import com.courseApp.constants.Constants;
import com.courseApp.constants.Exceptions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

class DrawingCanvas extends JComponent {

    private final String termType;
    private final String calendarType;
    private final Map<String, Map<String, ArrayList<String>>> rawSchedule;

    public DrawingCanvas(String termType, String calendarType,
                         Map<String, Map<String, ArrayList<String>>> rawSchedule) {
        this.termType = termType;
        this.calendarType = calendarType;
        this.rawSchedule = rawSchedule;

    }

    public void paintComponent(Graphics g) {
        Graphics2D calendar = (Graphics2D) g;
        calendar.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        UsePaintable fallCalendar = null;
        UsePaintable winterCalendar = null;
        if (this.calendarType.equals(Constants.TYPE_WORKDAY)) {
            fallCalendar = new WorkdayCalendar(CalendarPresenter.scheduleProcessor(Constants.FALL_TERM, rawSchedule));
            winterCalendar = new WorkdayCalendar(CalendarPresenter.scheduleProcessor(Constants.WINTER_TERM, rawSchedule));
        }
        generateHeader(calendar, this.termType);
        typeDecorator(calendar, fallCalendar, winterCalendar, this.termType, this.rawSchedule);
    }


        //lines
//        for (int i = 0; i < 17; i++) {
//            calendar.draw(DrawingCanvas.drawColumn(0, Constants.CELL_HEIGHT * i, Constants.CELL_LENGTH * 6));
//        }
//        //rows
//        for (int i = 0; i < 7; i++) {
//            calendar.draw(DrawingCanvas.drawRow(Constants.CELL_LENGTH * i, Constants.CELL_HEIGHT, Constants.CELL_HEIGHT * 15));
//        }
//        generateHeader(calendar, Constants.FALL_TERM);
//        DrawingCanvas.initiateWeekday(calendar);
//        DrawingCanvas.initiateTime(calendar);
//        calendar.setColor(Color.blue);
//        calendar.fill(DrawingCanvas.drawRectangle(Constants.CELL_LENGTH * Constants.LOCATION_MO, Constants.CELL_HEIGHT*(9-6), 2));
//        calendar.setColor(Color.white);
//        calendar.drawString("CSC207S", Constants.GRAPH_GAP + Constants.CELL_LENGTH * Constants.LOCATION_MO -12,Constants.GRAPH_HEIGHT -20+Constants.CELL_HEIGHT*(9-6));
//        calendar.drawString("LEC0101", Constants.GRAPH_GAP + Constants.CELL_LENGTH * Constants.LOCATION_MO -12,Constants.GRAPH_HEIGHT -5+Constants.CELL_HEIGHT*(9-6));

//    private void headerGenerator(String termType){
//        String resultingHeader = "";
//        if (termType.equals(Constants.YEAR)){
//            resultingHeader += Constants.YEAR_HEADER;
//        }
//        if (termType.equals(Constants.FALL_TERM) || termType.equals(Constants.YEAR)){
//            resultingHeader += Constants.FALL_HEADER;
//        }
//        else {resultingHeader += Constants.WINTER_HEADER;}
//        return resultingHeader;
//    }

    public static Path2D.Double drawColumn(int x, int y, int length) {
        Path2D.Double p = new Path2D.Double();
        p.moveTo(x, y);
        p.lineTo(x + length, y);
        return p;
    }

    public static Path2D.Double drawRow(int x, int y, int length) {
        Path2D.Double p = new Path2D.Double();
        p.moveTo(x, y);
        p.lineTo(x, y + length);
        return p;
    }

    public static Rectangle2D.Double drawRectangle(int x, int y, int timeDifference) {
        return new Rectangle2D.Double(x, y, Constants.CELL_LENGTH,
                Constants.CELL_HEIGHT * timeDifference);
    }


    private void generateHeader(Graphics2D calendar, String termType){
        if (termType.equals(Constants.YEAR)){
            calendar.drawString(Constants.YEAR_HEADER,Constants.GRAPH_GAP, Constants.GRAPH_HEIGHT);
        }
        if (termType.equals(Constants.FALL_TERM) || termType.equals(Constants.YEAR)){
            calendar.drawString(Constants.FALL_HEADER,Constants.GRAPH_GAP, Constants.GRAPH_HEIGHT);
        }
        else {calendar.drawString(Constants.WINTER_HEADER,Constants.GRAPH_GAP, Constants.GRAPH_HEIGHT);}
    }


    public void saveImage(String name,String type, Container cp) throws IOException {
        BufferedImage image = new BufferedImage(cp.getWidth(),cp.getWidth(), BufferedImage.TYPE_INT_RGB);
        Graphics2D calendar = image.createGraphics();
        cp.printAll(calendar);
        calendar.dispose();
        ImageIO.write(image, type, new File(name+"."+type));
    }

    /**
     * Decorate the picture Calendar following the instruction of termType
     *
     * @param fallCalendar Fall Calendar of required type
     * @param winterCalendar Winter Calendar of required type
     * @param termType the String term(s) we want
     * @param rawSchedule the input map from the schedule, containing all the courses of the year.
     *
     */
    private void typeDecorator(Graphics2D calendar, UsePaintable fallCalendar,UsePaintable winterCalendar,String termType,
                                 Map<String, Map<String, ArrayList<String>>> rawSchedule){
        if (Objects.equals(termType, Constants.FALL_TERM)){
            fallCalendar.paint(calendar);}
        else if (Objects.equals(termType,Constants.WINTER_TERM)){
            winterCalendar.paint(calendar);}
        else if (Objects.equals(termType, Constants.YEAR)){
            fallCalendar.paint(calendar);
            generateHeader(calendar,Constants.WINTER_TERM );
            //Defected! still working!
            winterCalendar.paint(calendar);
        }
    }



}
