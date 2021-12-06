package com.courseApp.webDriver.drivers;

import com.courseApp.reviewService.ReviewServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "review")
public class ReviewWebDriver {


    private final ReviewServiceController rsc;

    @Autowired
    public ReviewWebDriver(ReviewServiceController rsc){
        this.rsc = rsc;
    }


    @RequestMapping(value = "course_review", method = POST)
    public String generateCourseReviewSummary(String course, HttpSession session){
        if(session.getAttribute("username") == null) {return "redirect:/login";}

        try{
            session.setAttribute("res", rsc.getInstReviewSummary(course).toString());
            return "redirect:/result";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("res", "Course not found.");
            return "redirect:/result";
        }
    }

    @RequestMapping(value = "instructor_review", method = POST)
    public String generateInstReviewSummary(String course, String inst,HttpSession session){
        if(session.getAttribute("username") == null) {return "redirect:/login";}

        try{
            session.setAttribute("res", rsc.getUserReviewSummary(course, inst).toString());
            return "redirect:/result";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("res", "Course and instructor not found.");
            return "redirect:/result";
        }
    }

    @RequestMapping(value = "create_review", method = POST)
    public String createUserReview(String course, String inst, double generalRate, double difficultyRate, String reviewString ,HttpSession session){
        if(session.getAttribute("username") == null) {return "redirect:/login";}

        try{
            rsc.createNewUserReview(course, inst, (String) session.getAttribute("username"), generalRate, difficultyRate, reviewString);
            return generateInstReviewSummary(course, inst, session);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("res", "Creation failed!");
            return "redirect:/result";
        }
    }

    @RequestMapping(value = "inst_recommend", method = POST)
    public String recommendInst(String course, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        try {
            session.setAttribute("res", rsc.getInstRank(course));
            return "redirect:/result";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("res", "Recommendation failed!");
            return "redirect:/result";
        }
    }

}


