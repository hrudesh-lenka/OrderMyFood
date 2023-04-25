package com.ordermanagementservice.controller.impl;

import com.ordermanagementservice.controller.UserController;
import com.ordermanagementservice.entity.User;
import com.ordermanagementservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Optional<User>> getUserById(Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUser(User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}
