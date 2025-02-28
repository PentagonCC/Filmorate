package com.example.filmorate.storage;

import com.example.filmorate.model.User;

import java.util.List;

public interface UserStorage {

    User create(User user);

    User update(User user);

    List<User> findAllUsers();

    User getUserById(int id);

    List<User> getFriendsByUserId(Integer userId);

}
