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

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        log.info("GET / user / id / {}", id);
        return userStorage.getUserById(Integer.parseInt(id));
    }

    @PutMapping("/{id}/friends/{friendId}")
    public User addFriend(@PathVariable String id, @PathVariable String friendId){
        log.info("PUT / user / id / friends / friendId / {}", friendId);
        return userService.addFriend(Integer.parseInt(id), Integer.parseInt(friendId));
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public User deleteFriend(@PathVariable String id, @PathVariable String friendId){
        log.info("DELETE / user / id / friends / friendId / {}", friendId);
        return userService.deleteFriend(Integer.parseInt(id), Integer.parseInt(friendId));
    }

    @GetMapping("/{id}/friends")
    public List<User> getAllFriends(@PathVariable String id){
        log.info("GET / user / id / friends / {}", id);
        return userService.getFriends(Integer.parseInt(id));
    }

    @GetMapping("/{id}/friends/mutual/{otherId}")
    public List<User> getMutualFriends(@PathVariable String id, @PathVariable String otherId){
        log.info("GET / user / id / friends / mutual / otherId / {}", id);
        return userService.findMutualFriends(Integer.parseInt(id), Integer.parseInt(otherId));
    }
}
