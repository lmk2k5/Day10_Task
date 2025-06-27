package in.edu.kristujayanti;

import com.mongodb.client.MongoDatabase;
import in.edu.kristujayanti.handlers.CourseHandler;
import in.edu.kristujayanti.handlers.StudentHandler;
import in.edu.kristujayanti.services.CourseService;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        // Student routes
        new StudentHandler(vertx).route(router);

        // Course routes
        MongoDatabase db = MongoConnection.getDatabase();
        CourseService courseService = new CourseService(db);
        CourseHandler courseHandler = new CourseHandler(courseService);
        courseHandler.route(router);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8888, http -> {
                    if (http.succeeded()) {
                        System.out.println("Server running on http://localhost:8888");
                    } else {
                        System.out.println("Failed to start server: " + http.cause());
                    }
                });
    }
}
