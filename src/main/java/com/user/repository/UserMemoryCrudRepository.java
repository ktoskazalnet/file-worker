package com.user.repository;

import com.user.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserMemoryCrudRepository implements UserCrudRepository {

    private final List<User> userList = new ArrayList<>();

    @Override
    public User add(User user) {
        int andIncrementCurrentId = User.getAndIncrementCurrentId();
        user.setId(andIncrementCurrentId);
        userList.add(user);
        return user;
    }

    @Override
    public boolean deleteById(int id) {
        User user = new User();
        user.setId(id);
        return userList.remove(user);
    }

    @Override
    public User update(User updateWith) {
        int index = userList.indexOf(updateWith);
        return userList.set(index, updateWith);
    }

    @Override
    public List<User> getAll() {
        return Collections.unmodifiableList(userList);
    }

    @Override
    public Optional<User> getById(int id) {
        for (User u : userList) {
            if (u.getId() == id) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}
