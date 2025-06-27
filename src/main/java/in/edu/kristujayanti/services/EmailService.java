package in.edu.kristujayanti.services;

import io.vertx.core.Vertx;
import io.vertx.ext.mail.*;

public class EmailService {
    private final MailClient mailClient;
    public EmailService(Vertx vertx) {
        MailConfig config = new MailConfig()
                .setHostname("smtp.gmail.com")
                .setPort(587)
                .setStarttls(StartTLSOptions.REQUIRED)
                .setUsername("lohithm978@gmail.com")
                .setPassword("<removed for my privacy>");
        mailClient = MailClient.createShared(vertx, config);
    }
    public void sendEmail(String to, String subject, String body) {
        MailMessage message = new MailMessage()
                .setFrom("your_email@gmail.com")
                .setTo(to)
                .setSubject(subject)
                .setText(body);
        mailClient.sendMail(message, res -> {
            if (res.succeeded()) {
                System.out.println("Email sent!");
            } else {
                res.cause().printStackTrace();
            }
        });
    }
}
