package com.example.web;

import com.example.model.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController                     // Marks class as a controller
@RequestMapping("/api/users")       // The URI that this controller is connected to
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")                // What to do with a POST request
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        User user1 = userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<User>> returnUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users = userService.getUsers();
        return new ResponseEntity<ArrayList<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Messes up the created/updated fields, do we want the id to have to be in the json or pulled from the @PathVariable?
    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        User user1 = userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    // Patch is long, but I like it better than put :/
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser, @PathVariable Long id) {
        Optional<User> storedUser = userService.getUser(id);
        if (!storedUser.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (updatedUser.getFirstName() != null) {
            storedUser.get().setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            storedUser.get().setLastName(updatedUser.getLastName());
        }

        User user1 = userService.saveOrUpdateUser(storedUser.get());
        return new ResponseEntity<User>(storedUser.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
