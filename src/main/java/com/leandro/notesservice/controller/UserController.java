package com.leandro.notesservice.controller;

import javax.validation.Valid;

import com.leandro.notesservice.entity.User;
import com.leandro.notesservice.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        userRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/signup")
    public boolean signUp(@RequestBody @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;

    }

    @PutMapping("/update")
    public User updateUser(@RequestHeader("username") String oldUser, @RequestBody @Valid User newUser,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return null;
        }

        User user = userRepository.findByUsername(oldUser);

        if (user != null) {
            user.setUsername(newUser.getUsername());
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestHeader("username") String username) {

        User user = userRepository.findByUsername(username);

        if (user != null) {
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }

    }
}