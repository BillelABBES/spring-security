package com.doranco.security.services;

import com.doranco.security.entities.User;

public interface UserService {
    User createProf(User user);
    User createStudent(User user);
}
