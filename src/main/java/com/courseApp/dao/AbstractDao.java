package com.courseApp.dao;

import com.courseApp.constants.Constants;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDao {

    protected MongoDatabase getDatabase() {
        //Suppress MongoDB logger
        Logger mongoLogger = Logger.getLogger(Constants.DB_LOGGER);
        mongoLogger.setLevel(Level.SEVERE);
        //Establish connection to mongoDB
        ConnectionString connectionString = new ConnectionString(Constants.DB_CONNECTION);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient.getDatabase(Constants.DB_DATABASE_NAME).withCodecRegistry(Constants.CODEC_REGISTRY);
    }
}
