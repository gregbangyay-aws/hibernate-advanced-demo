package com.spring.hibernate_advanced_demo.dao;

import java.util.List;

import com.spring.hibernate_advanced_demo.entity.Course;
import com.spring.hibernate_advanced_demo.entity.Instructor;
import com.spring.hibernate_advanced_demo.entity.Student;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findById(int id);
    void deleteInstructorId(int id);
    Instructor findInstructorDetailById(int id);
    void deleteInstructorDetail(int id);
    List<Course>findCoursesByInstructorId(int instructorId);
    Instructor findInstructorByIdJoinFetch(int instructorId);
    void update(Instructor instructor);
    void deleteInstructorById(int instructorId);
    void save(Course course);
    Course findCourseAndReviewsById(int courseId);
    void deleteCourseById(int courseId);
    Course findCourseAndStudentsById(int courseId);
    Student findStudentAndCoursesById(int student);
    void deleteStudentById(int studentId);
}
