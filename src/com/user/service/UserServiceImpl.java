package com.user.service;

import com.user.exception.UserNotFoundException;
import com.user.model.User;
import com.user.repository.UserCrudRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserCrudRepository userCrudRepository;

    public UserServiceImpl(UserCrudRepository repository) {
        this.userCrudRepository = repository;
    }

    @Override
    public User add(User user) {
        //check if exist by email
        return userCrudRepository.add(user);
    }

    @Override
    public boolean deleteById(int id) {
        return userCrudRepository.deleteById(id);
    }

    @Override
    public User update(User updateWith) {
        if (userCrudRepository.getById(updateWith.getId()).isPresent()) {
            return userCrudRepository.update(updateWith);
        }

        throw new UserNotFoundException("not found");
    }

    @Override
    public List<User> getAll() {
        return userCrudRepository.getAll();
    }

    @Override
    public User getById(int id) {
        return userCrudRepository.getById(id)
                .orElseThrow(() -> new UserNotFoundException("not found"));
    }
}
