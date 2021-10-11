//package com.courseApp.DAO;
//
//import com.courseApp.constants.Constants;
//import com.courseApp.entity.User;
//import com.mongodb.*;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//import java.util.List;
//
//import static com.mongodb.client.model.Filters.eq;
//
//
//public class UserDaoImpl implements UserDAO{
//    private final String userName;
//    private final String password;
//    private final MongoCollection<Document> collection;
//
//
//    public UserDaoImpl(String username, String password) {
//        this.userName = username;
//        this.password = password;
//        this.collection = this.getCollection();
//    }
//
//    @Override
//    public User queryUser() {
//        return null;
//    }
//
//
//    @Override
//    public Boolean queryUserState() {
//        return !((List<Document>) this.queryByUserName()).isEmpty();
//    }
//
//    @Override
//    public String queryUserRole() {
//        return null;
//    }
//
//    @Override
//    public Boolean registerUser() {
//
//        Document person = new Document("name", this.userName)
//                .append("address", new Document("street", "123 Fake St")
//                        .append("city", "Faketon")
//                        .append("state", "MA")
//                        .append("zip", 12345));
//        this.collection.insertOne(person);
//        return true;
//
//    }
//
//
//    private Iterable<Document> queryByUserName(){
//        return this.collection.find(eq("name", this.userName));
//    }
//
//
//    private MongoCollection<Document> getCollection() {
//        ConnectionString connectionString = new ConnectionString(Constants.DB_CONNECTION);
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//        MongoClient mongoClient = MongoClients.create(settings);
//        MongoDatabase mongoDb =  mongoClient.getDatabase(Constants.DB_DATABASE_NAME);
//        return mongoDb.getCollection(Constants.DB_COLLECTION_NAME);
//    }
//
//    public static void main(String[] args) {
//        UserDaoImpl udi = new UserDaoImpl("Test","Test");
//        System.out.println(udi.queryUserState());
//        }
//    }
