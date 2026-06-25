package com.spring.hibernate_advanced_demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate_advanced_demo.dao.AppDao;
import com.spring.hibernate_advanced_demo.entity.Course;
import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.InstructorDetail;
import com.spring.hibernate_advanced_demo.entity.Review;
import com.spring.hibernate_advanced_demo.entity.Student;

@SpringBootApplication
public class HibernateAdvancedDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateAdvancedDemoApplication.class, args);
	}
	
	@Bean
    CommandLineRunner commandLineRunner(AppDao app) {
        return runner -> {
            //createInstructor(app);
            //findInstructor(app);
            //deleteInstructor(app);
            //findInstructorDetail(app);
//            deleteInstructorDetail(app);
//            createInstructorWithCourses(app);
//            findInstructorWithCourses(app);
           // findCoursesForInstructor(app);
//            findInstructorWithCoursesJoinFetch(app);
//            updateInstructor(app);
//            deleteInstructorById(app);
//            createCourseAndReviews(app);
//            findCourseAndReviewsById(app);
//            deleteCourseAndReviews(app);
//            createCourseAndStudents(app);
//            findCourseAndStudentsById(app);
            //findStudentAndCoursesById(app);
            //addMoreCoursesForStudent(app);
            deleteCourseById(app);
            
        };
    }
	
    private void createInstructor(AppDao app) {
        // create the instructor and details
        Instructor ins = new Instructor("John", "Doe", "jandoe@example.com");
        InstructorDetail deets = new InstructorDetail("Math", "Reading");
        
        // associate the objects
        ins.setInstructorDeets(deets);
        
        // save the instructor
        // this will also save the details object because of CascadeType.ALL
        System.out.println("Saving instructor: " + ins);
        app.save(ins);
    }
    
    private void findInstructor(AppDao app) {
        int id = 1;
        Instructor ins = app.findById(id);

        System.out.println("Found instructor: " + ins);
    }
    
    private void deleteInstructor(AppDao app) {
        int id = 1;
        System.out.println("Deleting instructor with id: " + id);
        app.deleteInstructorId(id);
    }
    
    private void findInstructorDetail(AppDao app) {
        int id = 5;
        Instructor ins = app.findInstructorDetailById(id);

        System.out.println("Found instructor: " + ins);
        System.out.println("Instructor details: " + ins.getInstructorDeets());
    }
    
    private void deleteInstructorDetail(AppDao app) {
        int id = 4;
        System.out.println("Deleting instructor detail with id: " + id);
        app.deleteInstructorDetail(id);
    }
    
    private void createInstructorWithCourses(AppDao app) {
        // create the instructor and details
        Instructor ins = new Instructor("Clint", "Baldez", "nigg@example.com");
        InstructorDetail deets = new InstructorDetail("Brainrot", "Instagram reels");
        
        // associate the objects
        ins.setInstructorDeets(deets);
        
        // create courses
        Course course1 = new Course("PHILNITS 101");
        Course course2 = new Course("日本語 101");
        
        ins.add(course1);
        ins.add(course2);
        
        System.out.println("Courses saved: " + ins.getCourses());
        app.save(ins);
    }
    
    private void findInstructorWithCourses(AppDao app) {
        int id = 6;
        Instructor ins = app.findById(id);

        System.out.println("Found instructor: " + ins);
        System.out.println("Instructor courses: " + ins.getCourses());
    }
    
    private void findCoursesForInstructor(AppDao app) {
        int id = 6;
        System.out.println("Find instructor id: " + id);
        Instructor ins = app.findInstructorDetailById(id);
        
        System.out.println("Instructor: " + ins);
        
        // display courses for the instructor
        System.out.println("Courses: " + ins.getCourses());
        List<Course> courses = app.findCoursesByInstructorId(id);
        
        ins.setCourses(courses);
        
        System.out.println("Courses: " + ins.getCourses());
    }
    
    private void findInstructorWithCoursesJoinFetch(AppDao app) {
        int id = 6;
        System.out.println("Find instructor id: " + id);
        Instructor ins = app.findInstructorByIdJoinFetch(id);

        System.out.println("Instructor: " + ins);

        // display courses for the instructor
        System.out.println("Courses: " + ins.getCourses());
    }
    
    private void updateInstructor(AppDao app) {
        int id = 6;
        System.out.println("Find instructor id: " + id);
        Instructor ins = app.findById(id);

        System.out.println("Instructor: " + ins);

        // update instructor's first name
        ins.setFirstName("No0b");
        ins.setLastName("Get gud");
        app.update(ins);

        System.out.println("Updated instructor: " + ins);
    }
    
    private void deleteInstructorById(AppDao app) {
        int id = 7;
        System.out.println("Deleting instructor with id: " + id);
        app.deleteInstructorById(id);
    }
    
    private void createCourseAndReviews(AppDao app) {
        //  create a course
        Course course = new Course("How to speedrun ACTION training any %");
        // add some reviews
        course.addReview(new Review("Great course... you are my GOAT!"));
        course.addReview(new Review("Goofy ahh course"));
        course.addReview(new Review("What a dumb course, you are an idiot!"));
        
        System.out.println("Saving course: " + course);
        app.save(course);
    }
    
    private void findCourseAndReviewsById(AppDao app) {
        int id = 5;
        Course course = app.findCourseAndReviewsById(id);

        System.out.println("Course: " + course);
        System.out.println("Reviews: " + course.getReview());
    }
    
    private void deleteCourseAndReviews(AppDao app) {
        int id = 3;
        System.out.println("Deleting course with id: " + id);
        app.deleteCourseById(id);
    }
    
    private void createCourseAndStudents(AppDao app) {
        // Create a course 
        Course course = new Course("xD course");
        
        // Create the students
        Student student1 = new Student("Jose Mari", "Onii-chan", "pasko@example.com");
        Student student2 = new Student("Dreamybull", "xxx", "ambtaukam@blud.com");
        Student student3 = new Student("いのり", "水瀬", "inorin@idk.com");
        
        course.addStudent(student1);
        course.addStudent(student2);
        course.addStudent(student3);
        
        app.save(course);
        System.out.println("Successfully enrolled!");
    }
    
    private void findCourseAndStudentsById(AppDao app) {
        int id = 1;
        Course course = app.findCourseAndStudentsById(id);

        System.out.println("Course: " + course);
        System.out.println("Students: " + course.getStudents());
    }
    
    private void findStudentAndCoursesById(AppDao app) {
        int id = 1;
        Student student = app.findStudentAndCoursesById(id);

        System.out.println("Student: " + student);
        System.out.println("Courses: " + student.getCourses());
    }
    
    private void addMoreCoursesForStudent(AppDao app) {
        int id = 1;
        Student student = app.findStudentAndCoursesById(id);

        System.out.println("Student: " + student);
        System.out.println("Courses: " + student.getCourses());

        // create more courses
        Course course1 = new Course("How to speedrun ACTION training any %");
        Course course2 = new Course("How to respawn after death in real life");

        // add courses to student
        student.addCourse(course1);
        student.addCourse(course2);

        // save the courses
        app.save(course1);
        app.save(course2);

        System.out.println("Successfully added more courses for the student!");
    }
    
    private void deleteCourseById(AppDao app) {
        int id = 1;
        System.out.println("Deleting course with id: " + id);
        app.deleteCourseById(id);
        
    }
    
    private void deleteStudentById(AppDao app) {
        int id = 1;
        System.out.println("Deleting student with id: " + id);
        app.deleteStudentById(id);
    }

}
