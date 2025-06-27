package in.edu.kristujayanti.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class CourseService {
    private final MongoCollection<Document> courses;
    public CourseService(MongoDatabase db) {
        this.courses = db.getCollection("courses");
    }
    public void addCourse(String id, String title, int seats) {
        Document doc = new Document()
                .append("_id", id)
                .append("title", title)
                .append("availableSeats", seats);
        courses.insertOne(doc);
    }
    public Iterable<Document> getAllCourses() {
        return courses.find();
    }
}
