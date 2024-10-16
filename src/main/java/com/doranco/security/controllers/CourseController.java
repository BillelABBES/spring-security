package com.doranco.security.controllers;

import com.doranco.security.entities.Course;
import com.doranco.security.enums.RoleEnum;
import com.doranco.security.services.CourseService;
import com.doranco.security.utils.UserRoleExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
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
    @PreAuthorize("hasAuthority('PROF')")
    public ResponseEntity createCourse (@RequestBody Course course, Authentication authentication){
//        if (!UserRoleExtractor.isUserProf(authentication))
//            return new ResponseEntity("Vous n'avez pas le droit", HttpStatus.FORBIDDEN);

        return ResponseEntity.ok(
                courseService.createCourse(course)
        );
    }
}
