package com.doranco.security.controllers;

import com.doranco.security.entities.User;
import com.doranco.security.services.UserService;
import com.doranco.security.utils.UserRoleExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("me")
    public ResponseEntity getMe (Authentication authentication){
        Optional<User> user = userService.getUserByEmail(authentication.getName());

        if (user.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user.get());
    }

    @GetMapping("sayHello")
    public ResponseEntity sayHello(Authentication authentication){
        if (UserRoleExtractor.isUserProf(authentication))
            return ResponseEntity.ok("Vous êtes prof");
        if (UserRoleExtractor.isUserStudent(authentication))
            return ResponseEntity.ok("vous êtes étudiant flemmart");
        return ResponseEntity.ok("Vous n'êtes ni prof ni étudiant");
    }


}
