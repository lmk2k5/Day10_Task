package in.edu.kristujayanti.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import in.edu.kristujayanti.MongoConnection;
import org.bson.Document;
import java.util.UUID;

public class StudentService {
    MongoCollection<Document> students = MongoConnection.getDatabase().getCollection("students");
    MongoCollection<Document> courses = MongoConnection.getDatabase().getCollection("courses");
    public String registerStudent(String name, String email) {
        String password = UUID.randomUUID().toString().substring(0, 8);
        Document student = new Document("name", name).append("email", email)
                .append("password", password)
                .append("courseId", null);

        students.insertOne(student);
        return password;
    }
    public boolean login(String email, String password) {
        Document student = students.find(Filters.eq("email", email)).first();
        return student != null && student.getString("password").equals(password);
    }
    public boolean registerForCourse(String email, String courseId) {
        Document course = courses.find(Filters.eq("courseId", courseId)).first();
        if (course != null && course.getInteger("availableSeats") > 0) {
            String title = course.getString("title"); // ✅ extract title
            students.updateOne(Filters.eq("email", email), Updates.combine(
                            Updates.set("courseId", courseId),
                            Updates.set("courseTitle", title)));
            courses.updateOne(Filters.eq("courseId", courseId), Updates.inc("availableSeats", -1));
            return true;
        }
        return false;
    }
}
