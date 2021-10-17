package com.courseApp.constants;

import com.courseApp.driver.cmdline.commands.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;


/**
 * Constant class for storing constants, including parameters and calendar models.
 */
public class Constants {

 //////// Calendar Constants
    public final static int CELL_FILLING = 12;
    public final static int HOUR_HEIGHT = 4;
    public final static int START_TIME = 8;
    public final static int START_HEIGHT = 5;
    public final static int TIME_DIFFERENCE_MIN = 1;
    public final static int LOCATION_MO = 14;
    public final static int LOCATION_TU = 27;
    public final static int LOCATION_WE = 40;
    public final static int LOCATION_TH = 53;
    public final static int LOCATION_FR = 66;
    public final static int COURSE_CODE_LENGTH = 7;

    public static final String FALL_TERM = "F";
    public static final String WINTER_TERM = "S";
    public static final String YEAR = "Y";
    public static final String TIME_COMMA = ":";
    public static final String CHANGE_LINE = "\n";
    public static final String TYPE_WORKDAY = "Workday";
    public static final String YEAR_HEADER = "Calendar of the Year\n";
    public static final String FALL_HEADER = "Calendar of the Fall Term\n";
    public static final String WINTER_HEADER = "Calendar of the Winter Term\n";



    // NOTE THAT THIS FEATURE REQUIRE JAVA 16.
    final static String WORK_DAY_TABLE =
            """
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |            |     Mon    |     Tue    |     Wed    |     Thu    |     Fri    |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    8:00    |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    9:00    |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    10:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    11:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    12:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    13:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    14:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    15:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    16:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    17:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    18:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    19:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    20:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    21:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+
                    |            |            |            |            |            |            |
                    |    22:00   |            |            |            |            |            |
                    |            |            |            |            |            |            |
                    +------------+------------+------------+------------+------------+------------+""";

    public static List<String> getListedWorkday(){
        return new ArrayList<>(Arrays.asList(WORK_DAY_TABLE.split("\n")));
    }

 //////// UofT API constants
    public static final String UT_API_URL = "https://timetable.iit.artsci.utoronto.ca/api/20219/" +
            "courses?org=&code={courseCode}&section={courseTerm}";

    public static final String COURSE_CODE = "{courseCode}";
    public static final String COURSE_TERM = "{courseTerm}";
    public static final String COURSE_DESCRIPTION = "courseDescription";
    public static final String COURSE_TITLE = "courseTitle";
    public static final String MEETING = "meetings";
    public static final String SCHEDULE = "schedule";
    public static final String MEETING_START_TIME = "meetingStartTime";
    public static final String MEETING_END_TIME = "meetingEndTime";
    public static final String MEETING_DAY = "meetingDay";
    public static final String PREREQUISITE = "prerequisite";
    public static final String SECTION_MARKER = "-";
    public static final String DESCRIPTION_FORMATTER_1 = "<p>";
    public static final String DESCRIPTION_FORMATTER_2 = "</p>";





   //////// User MangoDB constants
    public static final String DB_CONNECTION = "mongodb+srv://bugmaker:wmiIpcMxxGSnSOzU@clusterbugmaker." +
            "28uz2.mongodb.net/myFirstDatabase?retryWrites=true";

    public static final String DB_DATABASE_NAME = "bugMakerProject";
    public static final String DB_COLLECTION_NAME = "userSheet";


    //////// Course Code constants
    public static final int COURSE_CODE_FLAG = 7;
    public static final int COURSE_CODE_SECTION_FLAG = 3;
    public static final int COURSE_CODE_W_SECTION_LENGTH = 14;


    //////// Course Presentation constants
    public static final String TRI_TAB = "          ";
    public static final String TITLE = "- Title: ";
    public static final String DESCRIPTION = "- Description: ";
    public static final String SECTION = "- Section:";
    public static final String NAME_PREREQUISITE ="- Prerequisite: ";
    public static final String LONG_LINE = " ---------- ---------- ---------- ---------- ---------- ----------";

    //////// Uer DAO constants
    public static final String USERNAME = "username";
    public static final String COURSE_LIST = "course_list";
    public static final String WISH_LIST = "wish_list";
    public static final String SCHEDULE_LIST = "schedule_list";
    public static final String USER_ROLE = "userRole";
    public static final String REGULAR_USER = "regularUser";
    public static final String PASSWORD = "password";
    public static final int  DEFAULT_SCHEDULE = 0;


    //////// Dictionaries of commands
    public static final Hashtable<String, Command> ACCOUNT_COMMAND_DIC = new Hashtable<String, Command>();
    public static final Hashtable<String, UserCommand> USER_COMMAND_DIC = new Hashtable<String, UserCommand>();
    public static final Hashtable<String, CourseCommand> COURSE_COMMAND_DIC = new Hashtable<String, CourseCommand>();
    public static final Hashtable<String, CalendarCommand> CALENDAR_COMMAND_DIC = new Hashtable<String, CalendarCommand>();

    static{
        // user commands
        ACCOUNT_COMMAND_DIC.put("userLogin", new UserLogin());
        ACCOUNT_COMMAND_DIC.put("userRegister", new UserRegister());
        ACCOUNT_COMMAND_DIC.put("quitSession", new QuitSession());

        USER_COMMAND_DIC.put("userClearCourseList", new UserClearCourseList());
        USER_COMMAND_DIC.put("clearWishList", new UserClearWishList());
        USER_COMMAND_DIC.put("userClearScheduleList", new UserClearScheduleList());
        USER_COMMAND_DIC.put("rmCourse", new RmCourse());
        USER_COMMAND_DIC.put("rmWish", new RmWish());
        USER_COMMAND_DIC.put("getCourseList", new GetCourseList());
        USER_COMMAND_DIC.put("getWishList", new GetWishList());
        USER_COMMAND_DIC.put("getScheduleList", new GetScheduleList());
        USER_COMMAND_DIC.put("addCourse", new AddCourse());
        USER_COMMAND_DIC.put("addWish", new AddWish());
        USER_COMMAND_DIC.put("getLatestSchedule", new GetLatestSchedule());

        COURSE_COMMAND_DIC.put("getCourseGeneralInformation", new GetCourseGeneralInformation());
        COURSE_COMMAND_DIC.put("getSectionInformation", new GetSectionInformation());

    }


}
