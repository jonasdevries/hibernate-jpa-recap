package be.jslm.jpa.hibernate.demo.repository;

import be.jslm.jpa.hibernate.demo.DemoApplication;
import be.jslm.jpa.hibernate.demo.entity.Course;
import be.jslm.jpa.hibernate.demo.entity.Passport;
import be.jslm.jpa.hibernate.demo.entity.Student;
import org.hamcrest.Matchers;
import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StudentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    @Test(expected = org.hibernate.LazyInitializationException.class)
    public void fetchLazyInitializationException() {

        //
        Student student = em.find(Student.class, 20001L);
        logger.info("student {}", student);

        //  I do expect a LazyInitializationException because of the end of the hibernate session
        logger.info("passport {}", student.getPassport());
    }

    @Test
    @Transactional // No LazyInitializationException because of the @Transactional
    public void retrieveStudentAndPassportDetails(){

        Student student = em.find(Student.class, 20001L);
        logger.info("student {}", student);
        logger.info("passport {}", student.getPassport());

        assertEquals("Ranga", student.getName());
        assertEquals("E123456", student.getPassport().getNumber());
    }

    @Test
    @Transactional
    public void retrievePassportAndGetStudent(){

        Passport passport = em.find(Passport.class, 40001L);
        logger.info("passport {}", passport);
        logger.info("student {}", passport.getStudent());

        assertEquals("E123456", passport.getNumber());
        assertEquals("Ranga", passport.getStudent().getName());

    }


}
