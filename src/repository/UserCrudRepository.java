package repository;

import model.User;

import java.io.IOException;
import java.util.List;

public interface UserCrudRepository {
    User add(User user) throws IOException;

    boolean deleteById(int id);

    User update(User updateWith);

    List<User> getAll();

    User getById(int id) throws UserNotFoundException;
}
