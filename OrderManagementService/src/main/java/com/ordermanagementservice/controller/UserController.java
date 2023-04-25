package com.ordermanagementservice.controller;

import com.ordermanagementservice.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/api/order/users")
public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<Optional<User>> getUserById(@PathVariable Long id);

    @PostMapping("/create")
    ResponseEntity<User> createUser(@RequestBody User user);

    @PutMapping("/update")
    ResponseEntity<User> updateUser(@RequestBody User user);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id);


}
