package com.user.service;

import com.user.model.User;

import java.util.List;

public interface UserService {
    User add(User user);

    boolean deleteById(int id);

    User update(User updateWith);

    List<User> getAll();

    User getById(int id);
}
