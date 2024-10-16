package com.doranco.security.controllers;

import com.doranco.security.entities.User;
import com.doranco.security.security.JwtService;
import com.doranco.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

    @PostMapping("create-prof")
    public ResponseEntity createProf (@RequestBody User user){
        return ResponseEntity.ok(
                userService.createProf(user)
        );
    }

    @PostMapping("create-student")
    public ResponseEntity createStudent (@RequestBody User user){
        return ResponseEntity.ok(
                userService.createStudent(user)
        );
    }

    @PostMapping("login")
    public ResponseEntity login (@RequestBody Map<String, String> request){
        String email = request.get("email");
        String password = request.get("password");

        Optional<User> user = userService.getUserByEmail(email);
        if (user.isEmpty())
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

        if (!passwordEncoder.matches(password, user.get().getPassword()))
            return new ResponseEntity("Mot de passe incorrect", HttpStatus.UNAUTHORIZED);

        String jwt = jwtService.generateToken(user.get());
        return ResponseEntity.ok(jwt);
    }
}
