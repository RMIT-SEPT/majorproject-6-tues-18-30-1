package com.example.services;

import com.example.model.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user) {

        // business logic - add later
        return userRepository.save(user);
    }

    // Returns all the users
    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    // Returns the user if they exist or a null user
    public Optional<User> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    // Deletes if it exists
    public boolean deleteUser(Long id) {
        System.out.println("I can print stuff");
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}
