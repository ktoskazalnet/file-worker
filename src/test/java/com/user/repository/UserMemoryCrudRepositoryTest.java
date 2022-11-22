package com.user.repository;

import com.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class UserMemoryCrudRepositoryTest {
    private final UserCrudRepository repo = new UserMemoryCrudRepository();

    @Test
    void add_ShouldAddUser() {
        User user = new User(1, "Max", 20);
        Assertions.assertEquals(user, repo.add(user));
    }

    @Test
    void deleteById_ShouldDeleteUserById() {
        repo.add(new User(13, "Igor", 30));
        Assertions.assertTrue(repo.deleteById(13));
    }

    @Test
    void deleteById_ShouldReturnFalse() {
        Assertions.assertFalse(repo.deleteById(4));
    }

    @Test
    void update() {

    }

    @Test
    void getAll_ShouldReturnUnmodifiableListOfUsers() {
        Assertions.assertIterableEquals(List.<User>of(), repo.getAll());
    }

    @Test
    void getById_ShouldGetUserById() {
        User user = new User(1, "Max", 20);
        repo.add(user);

        Assertions.assertEquals(Optional.of(user), repo.getById(1));
    }

    @Test
    void getById_ShouldReturnOptionalEmpty() {
        Assertions.assertEquals(Optional.empty(), repo.getById(20));
    }
}