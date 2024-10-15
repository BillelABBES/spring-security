package com.doranco.security.services;

import com.doranco.security.entities.User;

import java.util.Optional;

public interface UserService {
    User createProf(User user);
    User createStudent(User user);
    String login (String email, String password);

    Optional<User> getUserByEmail(String email);
}
