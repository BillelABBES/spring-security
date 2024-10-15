package com.doranco.security.controllers;

import com.doranco.security.entities.User;
import com.doranco.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("create-prof")
    public ResponseEntity createProf (@RequestBody User user){
        return ResponseEntity.ok(
                userService.createProf(user)
        );
    }

    @PostMapping("create-Student")
    public ResponseEntity createStudent (@RequestBody User user){
        return ResponseEntity.ok(
                userService.createStudent(user)
        );
    }
}
