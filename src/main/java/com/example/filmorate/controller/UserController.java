package com.example.filmorate.controller;

import com.example.filmorate.model.User;
import com.example.filmorate.service.UserService;
import com.example.filmorate.storage.UserStorage;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserStorage userStorage;
    private final UserService userService;

    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserStorage userStorage, UserService userService) {
        this.userStorage = userStorage;
        this.userService = userService;
    }

    @PostMapping("/create")
    public User create(@Valid @RequestBody User user) {
        log.info("POST / user / {}", user.getLogin());
        return userStorage.create(user);
    }

    @PutMapping("/update")
    public User update(@Valid @RequestBody User user) {
        log.info("PUT / user / {}", user.getLogin());
        return userStorage.update(user);
    }

    @GetMapping
    public List<User> findAllUser() {
        return userStorage.findAllUsers();
    }


}
