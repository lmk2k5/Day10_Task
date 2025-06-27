package in.edu.kristujayanti.handlers;

import in.edu.kristujayanti.services.EmailService;
import in.edu.kristujayanti.services.StudentService;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class StudentHandler {
    StudentService studentService = new StudentService();
    EmailService emailService;

    public StudentHandler(Vertx vertx) {
        emailService = new EmailService(vertx);
    }

    public void route(Router router) {
        router.post("/register").handler(this::registerStudent);
        router.post("/login").handler(this::login);
        router.post("/enroll").handler(this::enrollCourse);
    }

    private void registerStudent(RoutingContext ctx) {
        var body = ctx.body().asJsonObject();
        String name = body.getString("name");
        String email = body.getString("email");
        String password = studentService.registerStudent(name, email);
        emailService.sendEmail(email, "Your Registration Password", "Your password is: " + password);
        ctx.response().end("Registered! Password sent to email.");
    }

    private void login(RoutingContext ctx) {
        var body = ctx.body().asJsonObject();
        boolean ok = studentService.login(body.getString("email"), body.getString("password"));
        ctx.response().end(ok ? "Login successful" : "Invalid credentials");
    }

    private void enrollCourse(RoutingContext ctx) {
        var body = ctx.body().asJsonObject();
        boolean success = studentService.registerForCourse(body.getString("email"), body.getString("courseId"));
        ctx.response().end(success ? "Course enrolled!" : "Course full or not found");
    }
}
