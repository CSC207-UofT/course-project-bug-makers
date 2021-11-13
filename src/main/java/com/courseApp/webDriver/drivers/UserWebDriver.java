package com.courseApp.webDriver.drivers;


import com.courseApp.userService.UserServiceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("user")
public class UserWebDriver {
    private static final Logger log  = LoggerFactory.getLogger(UserWebDriver.class);

    private final UserServiceController usc;

    @Autowired
    public UserWebDriver(UserServiceController usc){
        this.usc = usc;
    }

    @RequestMapping(value = "register", method = POST)
    public String register(String username, String password){
        log.debug("Register -> username: {}, password {}", username, password);
        try {
            if(!usc.userRegister(username, password)){
                throw new RuntimeException("Register Error");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "login", method = POST)
    public String login(String username, String password, HttpSession session){
        log.debug("Login -> username: {}, password {}", username, password);
        try {
            if(!usc.userLogin(username, password)){
                throw new RuntimeException("Login Error");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/login";
        }
        session.setAttribute("username", username);
        return "redirect:/introduction_dashboard";
    }


    @RequestMapping(value = "addCourse", method = POST)
    public String addCourse(String courseCode, HttpSession session){
        if(session.getAttribute("username") == null) {return "redirect:/login";}
        usc.addCourse(session.getAttribute("username").toString(), courseCode);
        return "redirect:/schedule_service";
    }


}
