package com.courseApp.userService;
import com.courseApp.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 * User Service Controller for user-related demands.
 */
@RestController
@RequestMapping("/rest")
public class UserServiceController implements ControlLoginRegister, ControlRM, ControlClear,
        ControlPresentInfo, ControlAddOne, ControlLatestSchedule{

    UserRequestProcessor urp;

    @Autowired
    public UserServiceController(UserRequestProcessor urp) {
        this.urp = urp;
    }

    /**
     *  User login
     *
     * @param username username
     * @param password password
     * @return true iff login is successful
     */
    @Override
    @RequestMapping(value = "/user/userLogin/{username}:{password}", method = POST, produces = "application/json")
    public boolean userLogin(@PathVariable String username, @PathVariable String password){
        urp.initWithUsernamePassword(username, password);
        return urp.userLogin();
    }

    /**
     * User register
     *
     * @param username username
     * @param password password
     * @return true iff register is successful
     */
    @Override
    @RequestMapping(value = "/user/userRegister/{username}:{password}", method = POST, produces = "application/json")
    public boolean userRegister(@PathVariable String username, @PathVariable String password) {
        urp.initWithUsernamePassword(username, password);
        return urp.userRegister();
    }

    /**
     * Clear the user's course list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @return true iff clear is successful
     */
    @Override
    @RequestMapping(value = "/user/userClearCourseList/{username}", method = DELETE, produces = "application/json")
    public boolean userClearCourseList(@PathVariable String username){
        urp.initWithUsername(username);
        return urp.clearCourseList();

    }

    /**
     * Clear the user's wish list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @return true iff clear is successful
     */
    @Override
    @RequestMapping(value = "/user/userClearWishList/{username}", method = DELETE, produces = "application/json")
    public boolean userClearWishList(@PathVariable String username){
        urp.initWithUsername(username);
        return urp.clearWishList();
    }

    /**
     * Clear the user's schedule list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @return true iff clear is successful
     */
    @Override
    @RequestMapping(value = "/user/userClearScheduleList/{username}", method = DELETE, produces = "application/json")
    public boolean userClearScheduleList(@PathVariable String username){
        urp.initWithUsername(username);
        return urp.clearScheduleList();
    }

    /**
     * Remove the given course in the user's course list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @param courseCode target course code
     * @return true iff removal is successful
     */
    @Override
    @RequestMapping(value = "/user/rmCourse/{username}:{courseCode}", method = DELETE, produces = "application/json")
    public boolean rmCourse(@PathVariable String username, @PathVariable String courseCode){
        urp.initWithUsername(username);
        return urp.removeOneCourse(courseCode);
    }

    /**
     * Remove the given course in the user's wish list.
     *
     * CALL THIS METHOD AFTER AUTHENTICATION
     *
     * @param username username
     * @param courseCode target course code
     * @return true iff removal is successful
     */
    @Override
    @RequestMapping(value = "/user/rmWish/{username}:{courseCode}", method = POST, produces = "application/json")
    public boolean rmWish(@PathVariable String username, @PathVariable String courseCode){
        urp.initWithUsername(username);
        return urp.removeOneWish(courseCode);
    }

    /**
     * Return a String representation of user's course list.
     *
     * @param username username
     * @return String representation of user course list
     */
    @RequestMapping(value = "/user/getCourseList/{username}", method = GET, produces = "application/json")
    public String getCourseList(@PathVariable String username){
        urp.initWithUsername(username);
        return urp.queryUserCourseList().toString();
    }

    /**
     * Return a String representation of user's wish list.
     *
     * @param username username
     * @return String representation of user wish list
     */
    @Override
    @RequestMapping(value = "/user/getWishList/{username}", method = GET, produces = "application/json")
    public String getWishList(@PathVariable String username){
        urp.initWithUsername(username);
        return urp.queryUserWishList().toString();
    }

    /**
     * Return a String representation of user's schedule list.
     *
     * @param username username
     * @return String representation of schedule list
     */
    @Override
    @RequestMapping(value = "/user/getScheduleList/{username}", method = GET, produces = "application/json")
    public String getScheduleList(@PathVariable String username){
        urp.initWithUsername(username);
        return urp.queryUserScheduleList().toString();
    }


    /**
     * Add course to course list.
     *
     * @param username username
     * @param courseCode target course code
     * @return true iff the insertion is successful
     */
    @Override
    @RequestMapping(value = "/user/addCourse/{username}:{courseCode}", method = PUT, produces = "application/json")
    public boolean addCourse(@PathVariable String username, @PathVariable String courseCode) {
        urp.initWithUsername(username);
        return urp.insertOneCourse(courseCode);
    }

    /**
     * Add course to the wish list.
     *
     * @param username username
     * @param courseCode target course code
     * @return ture iff the insertion is successful
     */
    @Override
    @RequestMapping(value = "/user/addWish/{username}:{courseCode}", method = PUT, produces = "application/json")
    public boolean addWish(@PathVariable String username, @PathVariable String courseCode) {
        urp.initWithUsername(username);
        return urp.insertOneWish(courseCode);
    }

    /**
     * Return the default schedule
     *
     * @param username username
     * @return Schedule at the default position
     */
    @Override
    @RequestMapping(value = "/user/getLatestSchedule/{username}", method = GET, produces = "application/json")
    public Schedule getLatestSchedule(@PathVariable String username) {
        urp.initWithUsername(username);
        return urp.queryUserScheduleList().get(0);
    }

}
