package in.edu.kristujayanti;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DB_NAME = "elective_system_Task10";

    public static MongoDatabase getDatabase() {
        MongoClient client = MongoClients.create(CONNECTION_STRING);
        return client.getDatabase(DB_NAME);
    }
}
