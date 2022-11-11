package service;

import model.User;
import repository.UserAlreadyExistException;
import repository.UserCrudRepository;
import repository.UserNotFoundException;

import java.io.IOException;
import java.util.List;

public class UserService {
    private final UserCrudRepository repository;

    public UserService(UserCrudRepository repository) {
        this.repository = repository;
    }

    public User add(User user) throws IOException {
        List<User> userList = getAll();

        try {
            for (User usr : userList) {
                if (usr.getID() == user.getID()) {
                    throw new UserAlreadyExistException("User with id: " + user.getID() + " already exist.");
                }
            }
            return repository.add(user);
        } catch (UserAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    public User update(User updateWith) {
        return repository.update(updateWith);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public User getById(int id) {
        try {
            return repository.getById(id);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
