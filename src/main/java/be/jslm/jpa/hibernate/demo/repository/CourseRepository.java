package be.jslm.jpa.hibernate.demo.repository;

import be.jslm.jpa.hibernate.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public Course save(Course course){
        if(course.getId() == null){
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Web Services in 100 Steps");
        em.persist(course1);

        Course course2 = findById(10001L);

        course2.setName("JPA in 50 Steps - Updated");

    }

    public void playWithEntityManager_part2(){
        Course course1 = new Course("Web Services in 100 Steps");
        em.persist(course1);
        em.flush();

        course1.setName("Web Services in 100 Steps - Updated");
        em.flush();

        Course course2 = new Course("Angular in 100 Steps");
        em.persist(course2);
        em.flush();

        course2.setName("Angular in 100 Steps - Updated");
        em.flush();
    }

}
