package com.doranco.security.ServicesImplem;

import com.doranco.security.entities.Course;
import com.doranco.security.repositories.CourseRepository;
import com.doranco.security.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseImplem implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
}
