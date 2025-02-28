package com.example.filmorate.service;

import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.model.User;
import com.example.filmorate.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage){
        this.userStorage = userStorage;
    }

    public User addFriend(Integer userId, Integer newFriendId) {
        User user = userStorage.getUserById(userId);
        User friend = userStorage.getUserById(newFriendId);
        if (user != null && friend != null) {
            user.getFriends().add(newFriendId);
            friend.getFriends().add(userId);
            return friend;
        }else throw new NotFoundException("Friend not found");
    }

    public User deleteFriend(Integer userId, Integer friendId) {
        User user = userStorage.getUserById(userId);
        User friend = userStorage.getUserById(friendId);
        if(user != null && friend != null){
            user.getFriends().remove(friendId);
            friend.getFriends().remove(userId);
            return friend;
        }else throw new NotFoundException("Friend not found");

    }

    public List<User> findMutualFriends(Integer userId, Integer friendId) {
        List<User> mutualFriends = new ArrayList<>();
        for(int id : userStorage.getUserById(userId).getFriends()){
            if(userStorage.getUserById(friendId).getFriends().contains(id)){
                mutualFriends.add(userStorage.getUserById(id));
            }
        }
        return mutualFriends;
    }

    public List<User> getFriends(Integer userId){
        return userStorage.getFriendsByUserId(userId);
    }
}
