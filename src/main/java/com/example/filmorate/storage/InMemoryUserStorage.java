package com.example.filmorate.storage;

import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.model.User;
import org.slf4j.LoggerFactoryFriend;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public User create(User user) {
        user.setFriends(new HashSet<>());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUserById(int id){
        if (users.containsKey(id)) {
            return users.get(id);
        }else throw new NotFoundException("User not found");
    }

    @Override
    public List<User> getFriendsByUserId(Integer userId){
        return findAllUsers().stream()
                .filter(user -> user.getFriends().contains(userId))
                .collect(Collectors.toList());
    }
}
