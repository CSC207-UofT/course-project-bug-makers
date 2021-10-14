package com.courseApp.DAO;

import com.courseApp.entity.User;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public interface UserDAO {

    User queryUser();

    Boolean queryUserState();

    String queryUserRole();

    Boolean registerUser();

}
