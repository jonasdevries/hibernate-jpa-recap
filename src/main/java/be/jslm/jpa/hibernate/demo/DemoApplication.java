package be.jslm.jpa.hibernate.demo;

import be.jslm.jpa.hibernate.demo.entity.Course;
import be.jslm.jpa.hibernate.demo.repository.CourseRepository;
import be.jslm.jpa.hibernate.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Course course = courseRepository.findById(10001L);
        logger.info("Course 1001 {}", course);

        courseRepository.save(new Course("Angular in 1000 Steps"));
    }
}
