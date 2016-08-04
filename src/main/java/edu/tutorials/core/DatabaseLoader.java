package edu.tutorials.core;

import edu.tutorials.course.Course;
import edu.tutorials.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courses;

    @Autowired
    public DatabaseLoader(CourseRepository courses) {
        this.courses = courses;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Building a RESTful Web Service", "https://spring.io/guides/gs/rest-service/");
        courses.save(course);
    }
}
