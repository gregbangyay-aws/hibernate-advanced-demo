package com.spring.hibernate_advanced_demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate_advanced_demo.entity.Course;
import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.InstructorDetail;
import com.spring.hibernate_advanced_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDaoImplement implements AppDao {
    
    private EntityManager em;
    
    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    @Override
    public Instructor findById(int id) {
        return em.find(Instructor.class, id);
    }

    @Override
    public void deleteInstructorId(int id) {
        // Retrieve the instructor
        Instructor instructor = em.find(Instructor.class, id);
        
        em.remove(instructor);
        
    }
    
    public Instructor findInstructorDetailById(int id) {
        return em.find(Instructor.class, id);
    }
    
    @Override
    @Transactional
    public void deleteInstructorDetail(int id) {
        // Retrieve the instructor detail
        InstructorDetail instructorDetail = em.find(InstructorDetail.class, id);
        
        instructorDetail.getInstructor().setInstructorDeets(null); // Break the bi-directional link

        // Remove the associated instructor detail
        if (instructorDetail != null) {
            em.remove(instructorDetail);
        }
    }
    
    public List<Course> findCoursesByInstructorId(int instructorId) {
        TypedQuery<Course> query = em.createQuery(
                "FROM Course WHERE instructor.id=:data", Course.class);
        query.setParameter("data", instructorId);
        
        List<Course> courses = query.getResultList();
        return courses;
        
    }
    
//    public Instructor findInstructorByIdJoinFetch(int instructorId) {
//        TypedQuery<Instructor> query = em.createQuery(
//                "SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id=:data",
//                Instructor.class);
//        query.setParameter("data", instructorId);
//
//        Instructor instructor = query.getSingleResult();
//        return instructor;
//    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int instructorId) {
        int id = 6;
        TypedQuery<Instructor> query = em.createQuery(
                "SELECT i FROM Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDeets WHERE i.id=:data",
                Instructor.class);
        query.setParameter("data", id);
        
        Instructor ins = query.getSingleResult();
        return ins;
    }
    
    @Override
    @Transactional
    public void update(Instructor instructor) {
        em.merge(instructor);
    }
    
    @Override
    @Transactional
    public void deleteInstructorById(int instructorId) {
        Instructor instructor = em.find(Instructor.class, instructorId);
        if (instructor != null) {
            em.remove(instructor);
        }
        
        // break the bi-directional link
        List<Course> courses = instructor.getCourses();
        if (courses != null) {
            for (Course course : courses) {
                course.setInstructor(null);
            }
        }
        em.remove(instructor);
    }
    
    @Override
    @Transactional
    public void save(Course course) {
        em.persist(course);
    }
    
    @Override
    public Course findCourseAndReviewsById(int courseId) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c JOIN FETCH c.review WHERE c.id=:data",
                Course.class);
        query.setParameter("data", courseId);
        
        Course course = query.getSingleResult();
        return course;
    }
    
    @Override
    @Transactional
    public void deleteCourseById(int courseId) {
        Course course = em.find(Course.class, courseId);
        if (course != null) {
            em.remove(course);
        }
    }
    
    @Override
    public Course findCourseAndStudentsById(int courseId) {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c JOIN FETCH c.students WHERE c.id=:data",
                Course.class);
        query.setParameter("data", courseId);

        Course course = query.getSingleResult();
        return course;
    }
    
    @Override
    public Student findStudentAndCoursesById(int studentId) {
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id=:data",
                Student.class);
        query.setParameter("data", studentId);
        
        Student student = query.getSingleResult();
        return student;
        
    }
    
    @Override
    public void deleteStudentById(int studentId) {
        Student student = em.find(Student.class, studentId);
        if (student != null) {
            // Get the courses associated with the student
            List<Course> courses = student.getCourses();
            
            for (Course course : courses) {
                course.getStudents().remove(student);
            }
        }
        // delete only the student
        // does not include the courses associated with the student
        em.remove(student);
        
    }

}
