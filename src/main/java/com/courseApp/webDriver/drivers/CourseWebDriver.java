package com.courseApp.webDriver.drivers;

import com.courseApp.calendarService.CalendarPresenter;
import com.courseApp.courseService.CourseServiceController;
import com.courseApp.userService.UserServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "course_service")
public class CourseWebDriver {

    private final UserServiceController usc;


    @Autowired
    public CourseWebDriver(UserServiceController usc){
        this.usc = usc;
    }


    @RequestMapping(value = "query_summary", method = POST)
    public String generateSummary(String courseCode, HttpSession session) throws Throwable {
        if(session.getAttribute("username") == null) {return "redirect:/login";}
        session.setAttribute("res", new CourseServiceController().getCourseGeneralInformation(courseCode));
        return "redirect:/result";
    }

}
