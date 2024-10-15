package com.doranco.security.ServicesImplem;

import com.doranco.security.entities.User;
import com.doranco.security.enums.RoleEnum;
import com.doranco.security.repositories.UserRepository;
import com.doranco.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImplem implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createProf(User user) {
//        assigner des Rôles aux utilisateurs
        user.getRoles().add(RoleEnum.PROF);
//        crypter le mot de passe
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User createStudent(User user) {
       user.getRoles().add(RoleEnum.STUDENT);
       String encodedPassword = passwordEncoder.encode(user.getPassword());
       user.setPassword(encodedPassword);
       return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
