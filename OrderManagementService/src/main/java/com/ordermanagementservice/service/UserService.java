package com.ordermanagementservice.service;

import com.ordermanagementservice.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public interface UserService {

    Optional<User> getUserById(Long id);

    User createUser( User user);

    User updateUser(@RequestBody User user);

    String deleteUser(@PathVariable Long id);
}
