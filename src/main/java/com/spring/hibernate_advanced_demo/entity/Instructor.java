package com.spring.hibernate_advanced_demo.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructor")
public class Instructor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDeets;
    
    @OneToMany(mappedBy = "instructor", fetch=FetchType.LAZY, cascade = { CascadeType.DETACH,
                                                                           CascadeType.MERGE, 
                                                                           CascadeType.PERSIST, 
                                                                           CascadeType.REFRESH })
    private List<Course> courses;
    

    public Instructor() {
    }
    
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setInstructorDeets(InstructorDetail instructorDeets) {
        this.instructorDeets = instructorDeets;
    }
    
    
    @Override
    public String toString() {
        return "Instructor [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", email=" + email + "]";
    }

    public String getInstructorDeets() {
        return instructorDeets.toString();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
    // add convenience method for bi-directional relationship
    public void add(Course tempCourse) {
        if (this.courses == null) {
            this.courses = new java.util.ArrayList<>();
        }
        // add course to instructor's list of courses
        this.courses.add(tempCourse);
        // tell course that it belongs to this instructor
        // 'this' refers to the current instance of Instructor
        tempCourse.setInstructor(this);
    }
    
}
