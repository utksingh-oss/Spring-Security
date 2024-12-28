package com.utkarsh.security.service;

import com.utkarsh.security.entity.User;
import com.utkarsh.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private static final String FAILURE = "Failure";
    private static final String SUCCESS = "Success";
    private static final String DUMMY_AUTH_TOKEN = "afjoofehfhe.fwfjewjefwoihofewheofihofehwoiehfoew.fwehowehfiof";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public ResponseEntity<String> verify(String username, String password) {
        User repoUser = userRepository.findByUsername(username);
        if (Objects.isNull(repoUser) || !repoUser.getPassword().equals(bCryptPasswordEncoder.encode(password))) {
            return new ResponseEntity<>(FAILURE, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(DUMMY_AUTH_TOKEN, HttpStatus.OK);
    }
}
