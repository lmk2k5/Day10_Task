package in.edu.kristujayanti.handlers;

import in.edu.kristujayanti.services.CourseService;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.bson.Document;

public class CourseHandler {
    private final CourseService courseService;

    public CourseHandler(CourseService courseService) {
        this.courseService = courseService;
    }

    public void route(Router router) {
        router.post("/courses").handler(this::addCourse);
        router.get("/courses").handler(this::getAllCourses);
    }

    private void addCourse(RoutingContext ctx) {
        var body = ctx.body().asJsonObject();
        String id = body.getString("id");
        String title = body.getString("title");
        int seats = body.getInteger("availableSeats");
        courseService.addCourse(id, title, seats);
        ctx.response().end("Course added");
    }

    private void getAllCourses(RoutingContext ctx) {
        var jsonArray = new io.vertx.core.json.JsonArray();

        for (Document course : courseService.getAllCourses()) {
            JsonObject obj = new JsonObject()
                    .put("id", course.getObjectId("_id").toHexString())
                    .put("title", course.getString("title"))
                    .put("availableSeats", course.getInteger("availableSeats", 0));
            jsonArray.add(obj);
        }

        ctx.response()
                .putHeader("Content-Type", "application/json")
                .end(jsonArray.encodePrettily());  // makes output human-readable
    }

}
