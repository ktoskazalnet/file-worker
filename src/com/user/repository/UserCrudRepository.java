package com.user.repository;

import com.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserCrudRepository {
    User add(User user);

    boolean deleteById(int id);

    User update(User updateWith);

    List<User> getAll();

    Optional<User> getById(int id);
}
