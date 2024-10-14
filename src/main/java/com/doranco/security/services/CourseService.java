package com.doranco.security.services;

import com.doranco.security.entities.Course;

import java.util.Optional;

public interface CourseService {
    Optional<Course> getCourseById(Long id);
    Course createCourse (Course course);
}
