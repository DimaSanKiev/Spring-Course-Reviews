package edu.tutorials.core;

import edu.tutorials.course.Course;
import edu.tutorials.course.CourseRepository;
import edu.tutorials.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courses;

    @Autowired
    public DatabaseLoader(CourseRepository courses) {
        this.courses = courses;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Java Programming", "https://www.coursera.org/specializations/java-programming");
        course.addReview(new Review(3, "Pretty good course for beginners."));
        courses.save(course);

        String[] templates = {
                "Up and running with %s",
                "%s Basics",
                "%s for Beginners",
                "%s for Professionals",
                "%s for Dummies",
                "Beginning Programming with %s",
                "%s: The Complete Course",
                "Under the hood: %s"
        };

        String[] buzzwords = {
                "Spring REST Data",
                "Java 9",
                "Java EE",
                "Scala",
                "Groovy",
                "Hibernate",
                "Spring"
        };

        List<Course> bunchOfCourses = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course c = new Course(title, "http://www.example.com");
                    c.addReview(new Review(i % 5, String.format("Moar %s please!", buzzword)));
                    bunchOfCourses.add(c);
                });
        courses.save(bunchOfCourses);
    }
}
