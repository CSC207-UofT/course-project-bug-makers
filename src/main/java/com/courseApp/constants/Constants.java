package com.courseApp.constants;

import com.courseApp.driver.cmdline.commands.*;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


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
    public final static int LOCATION_FIRST_DAY = 14;
    public final static int LOCATION_MO = 14;
    public final static int LOCATION_TU = 27;
    public final static int LOCATION_WE = 40;
    public final static int LOCATION_TH = 53;
    public final static int LOCATION_FR = 66;
    public final static int COURSE_CODE_LENGTH = 7;
    public static final int FIRST_DAY_POSITION = 2;
    public static final int COURSE_WEEKDAY_FLAG = 3;
    public final static int STRING_GAP = 13;

    public static final String FALL_TERM = "F";
    public static final String WINTER_TERM = "S";
    public static final String YEAR = "Y";
    public static final String TIME_COMMA = ":";
    public static final String CHANGE_LINE = "\n";
    public static final String TYPE_WORKDAY = "Workday";
    public static final String TYPE_MONDAY = "Monday";
    public static final String TYPE_TUESDAY = "Tuesday";
    public static final String TYPE_WEDNESDAY = "Wednesday";
    public static final String TYPE_THURSDAY = "Thursday";
    public static final String TYPE_FRIDAY = "Friday";
    public static final String TYPE_WEEK = "Week";
    public static final String YEAR_HEADER = "Calendar of the Year\n";
    public static final String FALL_HEADER = "Calendar of the Fall Term\n";
    public static final String WINTER_HEADER = "Calendar of the Winter Term\n";

    public static final String MONDAY = "MO";
    public static final String TUESDAY = "TU";
    public static final String WEDNESDAY = "WE";
    public static final String THURSDAY = "TH";
    public static final String FRIDAY = "FR";
    public static final String LECTURE = "LEC";
    public static final String TUTORIAL = "TUT";
    public static final String PRACTICAL = "PRA";
    public static final List<String> SECTION_TYPES = new ArrayList<>(Arrays.asList(LECTURE, TUTORIAL, PRACTICAL));


    // NOTE THAT THIS FEATURE REQUIRE JAVA 16.
    final static String WORKDAY_TABLE =
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|            |     Mon    |     Tue    |     Wed    |     Thu    |     Fri    |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    8:00    |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    9:00    |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    10:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    11:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    12:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    13:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    14:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    15:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    16:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    17:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    18:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    19:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    20:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    21:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n"+
                    "|            |            |            |            |            |            |\n"+
                    "|    22:00   |            |            |            |            |            |\n"+
                    "|            |            |            |            |            |            |\n"+
                    "+------------+------------+------------+------------+------------+------------+\n";
    final static String SINGLEDAY_TABLE =
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|            |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    8:00    |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    9:00    |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    10:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    11:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    12:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    13:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    14:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    15:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    16:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    17:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    18:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    19:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    20:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    21:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+\n" +
            "|            |            |\n" +
            "|    22:00   |            |\n" +
            "|            |            |\n" +
            "+------------+------------+";

    final static String WEEKDAY_TABLE =
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|            |     Sun    |     Mon    |     Tue    |     Wed    |     Thu    |     Fri    |     Sat    |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    8:00    |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    9:00    |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    10:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    11:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    12:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    13:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    14:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    15:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    16:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    17:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    18:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    19:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    20:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    21:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "|    22:00   |            |            |            |            |            |            |            |\n" +
            "|            |            |            |            |            |            |            |            |\n" +
            "+------------+------------+------------+------------+------------+------------+------------+------------+";

    public static List<String> getListedWorkday(){
        return new ArrayList<>(Arrays.asList(WORKDAY_TABLE.split("\n")));
    }
    public static List<String> getListedSingleday(){
        return new ArrayList<>(Arrays.asList(SINGLEDAY_TABLE.split("\n")));
    }
    public static List<String> getListedWeekday(){
        return new ArrayList<>(Arrays.asList(WEEKDAY_TABLE.split("\n")));
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
    public static final String INSTRUCTOR = "instructors";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String COMMA = ", ";
    public static final String INSTRUCTOR_SPLIT = ". ";
    public static final String LEC_FLAG = "LEC";
    public static final String LAST_REG = ",.$";





   //////// User MangoDB constants
    public static final String DB_CONNECTION = "mongodb+srv://bugmaker:wmiIpcMxxGSnSOzU@clusterbugmaker." +
            "28uz2.mongodb.net/myFirstDatabase?retryWrites=true";

    public static final String DB_DATABASE_NAME = "bugMakerProject";
    public static final String DB_USER_COLLECTION_NAME = "userSheet";
    public static final String DB_REVIEW_COLLECTION_NAME = "reviewSheet";
    public static final String DB_LOGGER = "org.mongodb.driver";
    public static final CodecRegistry CODEC_REGISTRY = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));


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

    //////// User DAO constants
    public static final String USERNAME = "username";
    public static final String COURSE_LIST = "courseList";
    public static final String WISH_LIST = "wishList";
    public static final String SCHEDULE_LIST = "scheduleList";
    public static final String USER_ROLE = "userRole";
    public static final String REGULAR_USER = "regularUser";
    public static final String ENCRYPTED_PASSWORD = "encryptedPassword";
    public static final String REVIEW_LIST = "reviewList";
    public static final int  DEFAULT_SCHEDULE = 0;


    //////// Dictionaries of commands
    public static final Hashtable<String, Command> ACCOUNT_COMMAND_DIC = new Hashtable<>();
    public static final Hashtable<String, UserCommand> USER_COMMAND_DIC = new Hashtable<>();
    public static final Hashtable<String, CourseCommand> COURSE_COMMAND_DIC = new Hashtable<>();
    public static final Hashtable<String, CalendarCommand> CALENDAR_COMMAND_DIC = new Hashtable<>();

    static{
        // user commands
        ACCOUNT_COMMAND_DIC.put("userLogin", new UserLogin());
        ACCOUNT_COMMAND_DIC.put("userRegister", new UserRegister());
        ACCOUNT_COMMAND_DIC.put("quitSession", new QuitSession());

        USER_COMMAND_DIC.put("userClearCourseList", new UserClearCourseList());
        USER_COMMAND_DIC.put("clearWishList", new UserClearWishList());
        USER_COMMAND_DIC.put("clearScheduleList", new UserClearScheduleList());
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
        COURSE_COMMAND_DIC.put("planCourse", new PlanCourse());

        CALENDAR_COMMAND_DIC.put("presentCalendar", new PresentCalendar());

    }

    // Review MongoDB Constants
    public static final String GENERAL_RATE = "generalRate";
    public static final String DIFFICULTY_RATE = "difficultyRate";
    public static final String RECOMMENDATION_SCORE = "recommendationScore";
    public static final String REVIEW_STRING = "reviewString";
    public static final String INST_NAME = "instructorName";
    public static final String INST_GENERAL_RATE = "instGeneralRate";
    public static final String INST_DIFFICULTY_RATE = "instDifficultyRate";
    public static final String INST_RECOMMENDATION_SCORE = "instRecommendationScore";
    public static final String USER_REVIEW_LIST = "userReviewList";
    public static final String COURSE_CODE_DB = "courseCode";
    public static final String COURSE_DIFFICULTY_RATE = "courseDifficultyRate";
    public static final String COURSE_GENERAL_RATE = "courseGeneralRate";
    public static final String INST_REVIEW_MAP = "instReviewMap";
    public static final String SECTION_LIST = "sectionList";
    public static final String SCHEDULE_MAP = "scheduleMap";
    public static final String INST_LIST = "instList";
    public static final String TIMESTAMP = "timestamp";


    //// Driver Constants

    public static final String WELCOME_LOGO =
"                                                                                                                                                                                                                   \n"+
"                                                                   `-/+osso+:-                                                                                .oooo+`                                              \n"+
"                                                                 `/ydddhhhhddo                                                                               `sddddd+                                              \n"+
"                        .::- .:///:.  .:///-`   ::::`     -:::` `sdddo-.``.-/`     .-////:.      -:::`    .:::.   -::. `-//-   .:////:-.     .:////-`        /ddh:ddd-      .::- .:///-`    `::: `-///:.           \n"+
"                        +ddhohhhdddhoshhhdddh:  +ddd+    -dddy` +dddo            .shdddhddhs:    oddd.    /ddd/   odds/hddd/ .shddhhhdd+   :yhdhyyddho`     -ddd/ oddy`     /dddohhhdddy/   -dddoyhhdddh+`         \n"+
"                        +dddh:..:hdddy:../dddh  `sddd-  `yddh.  yddd.           .hddh:..-sddd/   oddd.    /ddd/   odddds/::. /dddo.`.-:`  :dddo.``-ydds    `yddy  `hddo     /dddh:..-yddd-  -dddd/..-sddd+         \n"+
"                        +ddd+    sddd:    hddd`  `yddy` +ddd:   hddd.           +ddd/    `dddh   oddd.    /ddd/   oddd+      .ydddhs+-`   ydddsooooyddd`   +ddd/...+ddd:    /ddd+    :dddo  -ddds    `dddh         \n"+
"                        +ddd:    oddd-    yddd`   -ddd+.dddo    oddd/           oddd:     hddd`  oddd-    +ddd/   oddd-       `-+shdddh+  hdddsssssssss`  -ddddddddddddh.   /ddd/    -ddds  -ddds    `dddh         \n"+
"                        +ddd:    oddd.    yddd`    /dddsddy`    .hddh/.` ```.`  -ddds`  `:ddds   oddd/   .yddd/   oddd-      ``   `-hddd. +ddd/`     ``  `hddh++++++oddds   /dddy`  `oddd/  -dddh.  `/ddds         \n"+
"                        +ddd:    oddd.    yddd`     +ddddh.      -sdddhysyyhd/   /hddhssyddds`   -hdddssshhddd/   oddd-      +hso++ohdds` `ohddyo+oosy:  oddd/       oddd/  /dddhhssydddo`  -ddddhssydddy.         \n"+
"                        :sss-    +sss.    osss`     `yddd/         -/syyyyys+.    `/oyyyys+-      .+syyys/`+ss:   /sss.      -osyyyyso:`    ./oyyyyys+. .ssso`       `ssss` /ddd//syyyo:    -dddo:syyys:`          \n"+
"                                                   `:dddo                                                                                                                   /ddd:           -dddo                  \n"+
"                                                -ssydddo                                                                                                                    /ddd:           -dddo                  \n"+
"                                                :yhys+-                                                                                                                     /hhh:           -hhh+                  \n"+
"                                                                                                                                                                                                                   \n"+
"                                                                                                                                                                                                             \n";

    ////CmdShell Constants
    public static final String CMD_WELCOME = "Welcome to MyCoursePlanner";
    public static final String CMD_CHECK_ACCOUNT = "Do you have an account? Y/N";
    public static final String CMD_ENTER = "Enter command (type ?commands for the list of commands): ";
    public static final String HELPER = "?commands";
    public static final String HELPER_USER = "User commands: ";
    public static final String HELPER_COURSE = "Course commands: ";
    public static final String HELPER_CALENDAR = "Calendar commands: ";
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String ENTER_USERNAME = "Please enter your username: ";
    public static final String ENTER_PASSWORD = "Please enter your password: ";
    public static final String SUCCESS = "Login Success!";
    public static final String FAIL = "Login Failed :( Try again or register a new account. TryAgain/ Register ";
    public static final String REGISTER = "Register";
    public static final String CREATE_ACCOUNT = "Let's create an account for you! \n Please enter your username: ";
    public static final String CREATE_PASSWORD = "Please enter your password: ";

//    Azure Inference

    public static final String JSON_BODY ="{\"tasks\":{\"customClassificationTasks\":[{\"parameters\":{\"project-name\":\"courseTagClassified\",\"deployment-name\":\"prod\"}}]},\"displayName\":\"CustomTextPortal_singleClassification\",\"analysisInput\":{\"documents\":[{\"id\":\"document_singleClassification\",\"text\":\"<YOUR_DOCUMENT_HERE>\",\"language\":\"en-us\"}]}}";
    public static final String DOC_IDENTIFIER = "<YOUR_DOCUMENT_HERE>";

    public static final String PREDICTION_URL = "https://westus2.api.cognitive.microsoft.com/text/analytics/v3.2-preview.2/analyze";

    public static final String SUBSCRIPTION_KEY = "Ocp-Apim-Subscription-Key";

    public static final String SUBSCRIPTION_VALUE = "e4ad4518cabb46ab8de9c1f93ce7cdb5";

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String JSON_TYPE = "application/json";

    public static final String OPERATION_LOCATION = "operation-location";

    public static final String REGEX_CLASSIFICATION = "\"classification\":\\{\"category\":\"[0-9]+";

}
