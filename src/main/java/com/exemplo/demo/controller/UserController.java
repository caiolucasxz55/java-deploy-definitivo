package com.exemplo.demo.controller;

import com.exemplo.demo.model.User;
import com.exemplo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/password")
    public User changePassword(@RequestParam String email, @RequestParam String newPassword) {
        return service.changePassword(email, newPassword);
    }
    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        return service.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
    @GetMapping
    public List<User> listarTodos() {
        return service.listarTodos();
    }



}
