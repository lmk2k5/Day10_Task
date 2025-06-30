#  Day 10 Task

---

## 23IOTA10
Designed a **Java-based Elective Course Registration** System using ***MongoDB,Vertx and Postman***
- Students register for elective course selection.✅
- A random password is generated and emailed to the student upon registration.✅
- Students log in using their email and the password they received.✅
- After login, they can view available elective courses and register for one.✅
- The course's available seat count is reduced by one after a successful registration✅

## Technologies Used
- Java 17
- Vert.x Web
- MongoDB Compass (mongodb://localhost:27017)
- Postman (for API testing)
- SMTP (for email sending via Gmail)

---

## Project Structure

```
src
└── main
    └── java
        └── in
            └── edu
                └── kristujayanti
                    ├── Main.java              
                    ├── MongoConnection.java    
                    ├── handlers
                    │   ├── StudentHandler.java # 
                    │   └── CourseHandler.java  
                    └── services
                        ├── StudentService.java 
                        ├── CourseService.java  
                        └── EmailService.java   
```

---

## screenshots 

### 1️. Registration
![register_email_sent(postman).png](Screenshots/register_email_sent%28postman%29.png)
![email_ss.png](Screenshots/email_ss.png)

### 2️. Login
![login_email(pm).png](Screenshots/login_email%28pm%29.png)
![login_incorrect.png](Screenshots/login_incorrect.png)

### 3️. Courses
![courses(mongo).png](Screenshots/courses%28mongo%29.png)
![courses_nf_fill.png](Screenshots/courses_nf_fill.png)

### 4️. Get Courses
![GET_all(postman).png](Screenshots/GET_all%28postman%29.png)

### 5️. Enroll in Course
![course_registered.png](Screenshots/course_registered.png)
![course_number_reduced(postman).png](Screenshots/course_number_reduced%28postman%29.png)
![course_number_reduced(mongodb).png](Screenshots/course_number_reduced%28mongodb%29.png)
![course_enroll_sucess.png](Screenshots/course_enroll_sucess.png)
---

## method explaination

| File | Purpose |
|------|---------|
| `Main.java` | Bootstraps the Vert.x server and registers handlers |
| `MongoConnection.java` | Provides `MongoDatabase` instance |
| `StudentHandler.java` | Defines student-related endpoints |
| `CourseHandler.java` | Defines course-related endpoints |
| `StudentService.java` | Core student logic: register, login, enroll |
| `CourseService.java` | Adds and retrieves courses |
| `EmailService.java` | Sends registration email using Vert.x MailClient |


## Endpoints explanation

| Method | Endpoint         | endpoints                     | Body (JSON) Example                                      |
|--------|------------------|-------------------------------|----------------------------------------------------------|
| POST   | /register        | Register a new student        | { "name": "abc", "email": "abc@example.com" }`           |
| POST   | /login           | Login with email and password | { "email": "john@example.com", "password": "abcd1234" }` |
| POST   | /enroll          | Enroll student in a course    | { "email": "doe@example.com", "courseId": "CS101" }`     |
| GET    | /courses         | Get list of all courses       |                                                          |

***The project uses Vert.x Mail Client to send registration passwords to students via email. SMTP is configured for Gmail by the steps given:***
**Steps to Generate a Google App Password**

1. **Login** to your Google Account
2. Go to the Security tab.
3. find and click **App Passwords** under "Signing in to Google". .
4. For "Select device"and enter a name.
5. Click generate.
6. Copy the 16-character password shown use it in the project.

This project is a educational task given to **23IOTA10**