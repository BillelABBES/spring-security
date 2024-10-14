package com.doranco.security.controllers;

import com.doranco.security.entities.Course;
import com.doranco.security.repositories.CourseRepository;
import com.doranco.security.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("{id}")
    public ResponseEntity getCourseById(@PathVariable Long id){
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(course.get());
    }

    @PostMapping
    public ResponseEntity createCourse (@RequestBody Course course){
        return ResponseEntity.ok(
                courseService.createCourse(course)
        );
    }
}
