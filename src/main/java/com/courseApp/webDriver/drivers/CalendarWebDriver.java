package com.courseApp.webDriver.drivers;

import com.courseApp.calendarService.CalendarPresenter;
import com.courseApp.userService.UserServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "calendar_service")
public class CalendarWebDriver {


    private final UserServiceController usc;

    @Autowired
    public CalendarWebDriver(UserServiceController usc){
        this.usc = usc;
    }


    @RequestMapping(value = "generate", method = POST)
    public String generateCalendar(String term, String type, HttpSession session){
        session.setAttribute("res", new CalendarPresenter().presentCalendar(term, type,
                usc.getLatestSchedule(session.getAttribute("username").toString()).getScheduleMap()));
        return "redirect:/result";
    }

}
