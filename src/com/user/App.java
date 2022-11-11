package com.user;

import com.user.model.User;
import com.user.repository.UserCrudRepository;
import com.user.repository.UserFileCrudRepository;
import com.user.repository.UserMemoryCrudRepository;
import com.user.service.UserService;
import com.user.service.UserServiceImpl;

import java.io.File;

public class App {

    /*
                        Функциональные требования
        1) Добавлять в файл юзеров. Если юзер по id существует
        выкидывать исключение UserAlreadyExist (User add(User user)) throws UserAlreadyExist)
        2) Удалять юзера из файла по id (boolean deleteById(int id))
        3) Обновлять данные юзера по id (User update (User updateWith))
        4) Получение всего списка пользователей ( List<User> getAll())
        5) Получение пользователя по id (User getById(int id))
     */

    public static void main(String[] args) {
        try {

            UserCrudRepository repository;

            if (args[0].equals("file")) {
                File file = new File("users.txt");
                if (!file.exists()) {
                    boolean newFile = file.createNewFile();
                }

                System.out.println("Switched to file mode");
                repository = new UserFileCrudRepository(file);
            } else {
                System.out.println("Switched to memory mode");
                repository = new UserMemoryCrudRepository();
            }

            UserService service = new UserServiceImpl(repository);

            service.add(new User("Igor", 19));
            service.add(new User("Max", 29));
            service.add(new User("Nicolay", 30));
            service.add(new User("Maria", 20));
            service.add(new User("Alex", 21));
            service.add(new User("John", 35));
            service.add(new User("Sveta", 35));

            System.out.println(service.getById(6));

            service.deleteById(6);

            service.update(new User(1, "Bob", 45));

            System.out.println(service.getAll());

            service.add(new User(7, "Kelly", 38));

            System.out.println(service.getById(999));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}