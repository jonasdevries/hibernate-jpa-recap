package be.jslm.jpa.hibernate.demo.repository;

import be.jslm.jpa.hibernate.demo.DemoApplication;
import be.jslm.jpa.hibernate.demo.entity.Course;
import org.hamcrest.Matchers;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Test
    public void findById() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    public void findById_assert_isMatcher() {
        Course course = repository.findById(10001L);
        assertThat("use an assert_isMatcher", "JPA in 50 Steps", Matchers.is(course.getName()));
    }

    @Test
    @DirtiesContext
    public void deleteById_basic(){
        repository.deleteById(10002L);
        Course course = repository.findById(10002L);
        assertNull(course);
    }

    @Test
    @DirtiesContext
    public void updateCourse_happy_path(){

        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());

        // update details
        course.setName("JPA in 50 Steps - Updated");
        Course courseCreated = repository.save(course);
        assertEquals("JPA in 50 Steps - Updated", courseCreated.getName());
    }

    @Test
    @DirtiesContext
    public void saveCourse(){

        Course course = new Course("React in 200 Steps");
        Course courseCreated = repository.save(course);
        assertEquals("React in 200 Steps", courseCreated.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager(){
        logger.info("playWithEntityManager - started");
        repository.playWithEntityManager();
        logger.info("playWithEntityManager - ended");
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager_part2(){
        logger.info("playWithEntityManager part 2- started");
        repository.playWithEntityManager_part2();
        logger.info("playWithEntityManager part 2 - ended");
    }
}
