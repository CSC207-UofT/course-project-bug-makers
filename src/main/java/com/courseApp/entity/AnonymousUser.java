package com.courseApp.entity;
import java.util.Random;

/**
 * Anonymous user is a subclass of User class. This user entity is for temporary users of the app.
 * Any search history and calendars created during this session will not be saved into the database.
 * this user object should be deleted after the session is over.
 * Limited commands compared to RegularUser.
 */
public class AnonymousUser extends User {

    private Random rand= new Random();
    private int randomInt = rand.nextInt(999999);
    String anonymUsername = "User" + Integer.toString(randomInt);
    public AnonymousUser(String anonymUsername){
        super(anonymUsername);




    }
}
