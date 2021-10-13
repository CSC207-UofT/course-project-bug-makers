package com.courseApp.DAO;

import com.courseApp.constants.Constants;
import com.courseApp.entity.RegularUser;
import com.courseApp.entity.Schedule;
import com.courseApp.entity.User;
import com.courseApp.utils.PasswordEncoderMD5;
import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

/**
 * Implemented User DAO for user data querying, login/register services.
 */
public class UserDaoImpl implements UserDAO{
    private final String userName;
    private final String password;
    private final MongoCollection<Document> collection;


    public UserDaoImpl(String username, String password) {
        this.userName = username;
        this.password = password;
        this.collection = this.getCollection();
    }

    /**
     * Return a User obj, syncing with the database iff the password is correct.
     *
     * @return User obj iff password is correct
     */
    @Override
    public User queryUser() {
        if (Objects.equals(queryUserRole(), Constants.REGULAR_USER) & checkPassword()){
            return new RegularUser(this.userName,
                    queryCourseList(),
                    queryWishList(),
                    queryScheduleList(),
                    queryUserRole());
        }
        return null;
    }

    /**
     * Check if the username already exists in the DB.
     *
     * @return true iff the username is in the DB, otherwise, false
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
     * @return user's schedule list
     */
    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Schedule> queryScheduleList() {
        return (ArrayList<Schedule>) this.queryByUserName().iterator().next().get(Constants.SCHEDULE_LIST);

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
                .append(Constants.SCHEDULE_LIST, new ArrayList<Schedule>())
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
     * @return Iterator of username, note that we are only allowing one username to appear in the database for
     * once, so the queried item should be the first one in the iterator.
     */
    private Iterable<Document> queryByUserName(){
        return this.collection.find(eq(Constants.USERNAME, this.userName));
    }

    private MongoCollection<Document> getCollection() {
        //Suppress MongoDB logger
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);
        //Establish connection with mongoDB
        ConnectionString connectionString = new ConnectionString(Constants.DB_CONNECTION);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase mongoDb =  mongoClient.getDatabase(Constants.DB_DATABASE_NAME);
        return mongoDb.getCollection(Constants.DB_COLLECTION_NAME);
    }

    /**
     * Check the provided password with the one in the database.
     * Note that the encoding algorithm used is MD5.
     *
     * @return True iff the password is consistent, otherwise, false.
     */
    private boolean checkPassword(){
        return (PasswordEncoderMD5.encode(this.password))
                .equals(this.queryByUserName().iterator().next().get(Constants.PASSWORD));
    }





    public static void main(String[] args) {
        UserDaoImpl udi = new UserDaoImpl("TestRegister2","Test");
        System.out.println(udi.userLogin());
        System.out.println(1);
        }
    }
