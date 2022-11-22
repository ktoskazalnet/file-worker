package com.user.service;

import com.user.exception.UserNotFoundException;
import com.user.model.User;
import com.user.repository.UserCrudRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

class UserServiceImplTest {
    private final UserCrudRepository userCrudRepository = Mockito.mock(UserCrudRepository.class);
    private final UserServiceImpl userService = new UserServiceImpl(userCrudRepository);

    @AfterEach
    void afterEach() {
        Mockito.verifyNoMoreInteractions(userCrudRepository);
    }

    @Test
    void add() {
        User user = Mockito.mock(User.class);
        Mockito.when(userCrudRepository.add(user)).thenReturn(user);

        User add = userService.add(user);
        Mockito.verify(userCrudRepository).add(user);

        Assertions.assertEquals(user, add);
    }

    @Test
    void update_ShouldBeUpdate() {
        User user = Mockito.mock(User.class);
        Optional<User> optionalUser = Optional.of(user);

        Mockito.when(user.getId()).thenReturn(5);
        Mockito.when(userCrudRepository.getById(5)).thenReturn(optionalUser);
        Mockito.when(userCrudRepository.update(user)).thenReturn(user);

        User updateResult = userService.update(user);

        Mockito.verify(userCrudRepository).update(user);
        Mockito.verify(userCrudRepository).getById(5);

        Assertions.assertEquals(updateResult, user);
    }

    @Test
    void update_ShouldThrowUserNotFoundException() {
        User user = Mockito.mock(User.class);
        Optional<User> optionalUser = Optional.empty();

        Mockito.when(userCrudRepository.getById(anyInt())).thenReturn(optionalUser);

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.update(user));
        Mockito.verify(userCrudRepository).getById(anyInt());
    }

    @Test
    void getById_ShouldGetUser() {
        User user = Mockito.mock(User.class);
        Optional<User> optionalUser = Optional.of(user);

        Mockito.when(user.getId()).thenReturn(5);
        Mockito.when(userCrudRepository.getById(5)).thenReturn(optionalUser);

        User getByIdResult = userService.getById(5);

        Mockito.verify(userCrudRepository).getById(5);

        Assertions.assertEquals(getByIdResult, user);
    }

    @Test
    void getById_ShouldThrowUserNotFoundException() {
        User user = Mockito.mock(User.class);
        Optional<User> optionalUser = Optional.empty();

        Mockito.when(userCrudRepository.getById(anyInt())).thenReturn(optionalUser);

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getById(user.getId()));
        Mockito.verify(userCrudRepository).getById(anyInt());
    }

    @Test
    void deleteById_ShouldDeleteUserById() {
        Mockito.when(userCrudRepository.deleteById(anyInt())).thenReturn(true);
        Mockito.verify(userCrudRepository).deleteById(anyInt());

    }

    @Test
    void getAll_ShouldGetAll() {
        List<User> userList = Mockito.mock(ArrayList.class);

        Mockito.when(userCrudRepository.getAll()).thenReturn(userList);
        List<User> getAllResult = userCrudRepository.getAll();

        Mockito.verify(userCrudRepository).getAll();
        Assertions.assertIterableEquals(getAllResult, userList);
    }
}