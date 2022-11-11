import model.User;
import repository.UserCrudRepository;
import repository.UserFileCrudRepository;
import service.UserService;

import java.io.File;
import java.io.IOException;

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

    public static void main(String[] args) throws IOException {
        File file = new File("users.txt");
        if (!file.exists()) {
            boolean newFile = file.createNewFile();
        }

        UserCrudRepository repository = new UserFileCrudRepository(file);
        UserService service = new UserService(repository);

        service.add(new User(2, "Igor", 19));
        service.add(new User(1, "Max", 29));
        service.add(new User(3, "Nicolay", 30));
        service.add(new User(4, "Maria", 20));
        service.add(new User(5, "Alex", 21));
        service.add(new User(6, "John", 35));
        service.add(new User(7, "Sveta", 35));

        System.out.println(service.getById(6));
        service.deleteById(6);
        service.update(new User(1, "Bob", 45));

        System.out.println(service.getAll());

        service.add(new User(7, "Kelly", 38));
        System.out.println(service.getById(999));
    }
}