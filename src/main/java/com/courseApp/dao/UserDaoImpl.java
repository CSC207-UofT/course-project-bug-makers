package com.courseApp.dao;

import com.courseApp.constants.Constants;
import com.courseApp.courseService.ScheduleUpdater;
import com.courseApp.entity.Schedule;
import com.courseApp.entity.User;
import com.courseApp.utils.PasswordEncoderMD5;
import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

/**
 * Implemented User DAO for user data querying, login/register services.
 */
public class UserDaoImpl implements UserDAO{
    private final String userName;
    private String password;
    private final MongoCollection<Document> collection;
    private final Bson filter;


    /**
     * Constructor taking ControlPresentInfo username and password, for register/login service.
     *
     * @param username username
     * @param password password
     */
    public UserDaoImpl(String username, String password) {
        this.userName = username;
        this.password = password;
        this.collection = this.getCollection();
        this.filter = eq(Constants.USERNAME, this.userName);
    }

    /**
     * Constructor taking ControlPresentInfo username, for querying service. Note that this branch must be called
     * after the authentication is done.
     *
     * @param username username
     */
    public UserDaoImpl(String username) {
        this.userName = username;
        this.collection = this.getCollection();
        this.filter = eq(Constants.USERNAME, this.userName);
    }

    /**
     * Return a User obj, syncing with the database iff the password is correct.
     *
     * @return User obj iff password is correct
     */
    @Override
    public User queryUser() {
        if (Objects.equals(queryUserRole(), Constants.REGULAR_USER) & checkPassword()){
            return new User(this.userName,
                    queryCourseList(),
                    queryWishList(),
                    queryScheduleList(),
                    queryUserRole());
        }
        return null;
    }

    /**
     * Check if the username already exists ControlPresentInfo the DB.
     *
     * @return true iff the username is ControlPresentInfo the DB, otherwise, false
     */
    @Override
    public Boolean queryUserInDB() {
        return this.queryByUserName().iterator().hasNext();
    }

    /**
     * Query User's role
     *
     * @return user's role
     */
    @Override
    public String queryUserRole() {
        return (String) this.queryByUserName().iterator().next().get(Constants.USER_ROLE);
    }

    /**
     * Query user's course list
     *
     * @return user's course list
     */
    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<String> queryCourseList() {
        return (ArrayList<String>) this.queryByUserName().iterator().next().get(Constants.COURSE_LIST);
    }

    /**
     * Query user's wish list
     *
     * @return user's wish list
     */
    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<String> queryWishList() {
        return (ArrayList<String>) this.queryByUserName().iterator().next().get(Constants.WISH_LIST);

    }

    /**
     * Query user's schedule list
     *
     * @return user's schedule list with no schedule map ControlPresentInfo the schedule object.
     */
    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Schedule> queryScheduleList() {
        ArrayList<Schedule> res = new ArrayList<>();
        for (ArrayList<String> strings : (ArrayList<ArrayList<String>>)
                this.queryByUserName().iterator().next().get(Constants.SCHEDULE_LIST)) {
            res.add(new Schedule(strings));
        }
        ScheduleUpdater su = new ScheduleUpdater();
        for(Schedule schedule : res){
            su.updateScheduleMap(schedule);
        }
        return res;
    }

    /**
     * Update course list.
     *
     * @param courseList User course list
     * @return ture iff the update is successful
     */
    @Override
    public boolean updateCourseList(ArrayList<String> courseList) {
        this.collection.updateOne(this.filter, set(Constants.COURSE_LIST, courseList));
        return true;
    }

    /**
     * Update wish List
     *
     * @param wishList User wish list
     * @return ture iff the update is successful
     */
    @Override
    public boolean updateWishList(ArrayList<String> wishList) {
        this.collection.updateOne(this.filter, set(Constants.WISH_LIST, wishList));
        return true;
    }

    /**
     * Update schedule list. Note that the schedule map is omitted.
     *
     * @param scheduleList list of schedule
     * @return ture iff the update is successful
     */
    @Override
    public boolean updateScheduleList(ArrayList<Schedule> scheduleList) {
        this.collection.updateOne(this.filter, set(Constants.SCHEDULE_LIST,
                scheduleListArray2sectionListArray(scheduleList)));
        return true;
    }

    /**
     * User register service with user existence checked.
     *
     * @return true iff register is successful, otherwise, false
     */
    @Override
    public Boolean userRegister() {
        if (!queryUserInDB()){
        Document person = new Document(Constants.USERNAME, this.userName)
                .append(Constants.PASSWORD, PasswordEncoderMD5.encode(this.password))
                .append(Constants.COURSE_LIST, new ArrayList<String>())
                .append(Constants.WISH_LIST, new ArrayList<String>())
                .append(Constants.SCHEDULE_LIST, new ArrayList<ArrayList<String>>())
                .append(Constants.USER_ROLE, Constants.REGULAR_USER)
                ;
        this.collection.insertOne(person);
        return true;}
        return false;

    }

    /**
     * User Login service with password check and username check.
     *
     * @return ture iff login is successful, otherwise, false.
     */
    @Override
    public Boolean userLogin() {
        if (queryUserInDB()){return checkPassword();} return false;
    }


    /**
     * Query information by username.
     *
     * @return Iterator of username, note that we are only allowing one username to appear ControlPresentInfo the database for
     * once, so the queried item should be the first one ControlPresentInfo the iterator.
     */
    private Iterable<Document> queryByUserName(){
        return this.collection.find(eq(Constants.USERNAME, this.userName));
    }

    private MongoCollection<Document> getCollection() {
        //Suppress MongoDB logger
        Logger mongoLogger = Logger.getLogger( Constants.DB_LOGGER );
        mongoLogger.setLevel(Level.SEVERE);
        //Establish connection to mongoDB
        ConnectionString connectionString = new ConnectionString(Constants.DB_CONNECTION);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase mongoDb =  mongoClient.getDatabase(Constants.DB_DATABASE_NAME);
        return mongoDb.getCollection(Constants.DB_COLLECTION_NAME);
    }

    /**
     * Check the provided password with the one ControlPresentInfo the database.
     * Note that the encoding algorithm used is MD5.
     *
     * @return True iff the password is consistent, otherwise, false.
     */
    private boolean checkPassword(){
        return (PasswordEncoderMD5.encode(this.password))
                .equals(this.queryByUserName().iterator().next().get(Constants.PASSWORD));
    }

    /**
     * Convert list of Schedule objs to list of sectionList obj.
     *
     * @param scheduleList schedule list
     * @return section list
     */
    private ArrayList<ArrayList<String>> scheduleListArray2sectionListArray(ArrayList<Schedule> scheduleList){
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(Schedule schedule: scheduleList){
            res.add(schedule.getSectionList());
        }
        return res;
    }


//    public static void main(String[] args) {
//        UserDaoImpl udi = new UserDaoImpl("TestRegister2","Test");
//        ArrayList<String> th_schedule = new ArrayList<>();
//        th_schedule.add("17:00");
//        th_schedule.add("19:00");
//
//
//        ArrayList<String> tu_schedule = new ArrayList<>();
//        tu_schedule.add("11:00");
//        tu_schedule.add("14:00");
//
//
//        ArrayList<String> th_schedule2 = new ArrayList<>();
//        th_schedule2.add("8:00");
//        th_schedule2.add("9:00");
//
//
//        ArrayList<String> mo_schedule2 = new ArrayList<>();
//        mo_schedule2.add("10:00");
//        mo_schedule2.add("11:00");
//
//        Map<String, ArrayList<String>> day = new HashMap<>();
//        day.put("TH", th_schedule);
//
//
//        Map<String, ArrayList<String>> day2 = new HashMap<>();
//        day2.put("TH", th_schedule2);
//
//        Map<String, ArrayList<String>> day3 = new HashMap<>();
//        day3.put("TU", tu_schedule);
//
//        Map<String, ArrayList<String>> day4 = new HashMap<>();
//        day4.put("MO", mo_schedule2);
//
//        Map<String, Map<String, ArrayList<String>>> cad = new HashMap<>();
//        cad.put("CSC207FLEC0301", day);
//        cad.put("MAT223FLEC0601", day2);
//        cad.put("BIO230FLEC9901", day3);
//        cad.put("STA237FLEC5101", day4);
//
//        Schedule a = new Schedule(cad);
//        Schedule b = new Schedule(cad);
//
//        ArrayList<Schedule> replacement= new ArrayList<>();
//
//        replacement.add(a);
//        replacement.add(b);
//        System.out.println(udi.updateScheduleList(replacement));
//        ArrayList<Schedule> sl =  udi.queryScheduleList();
//        System.out.println(sl);
//        }
    }
