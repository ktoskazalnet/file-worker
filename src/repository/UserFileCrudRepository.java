package repository;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileCrudRepository implements UserCrudRepository {
    private final File file;

    public UserFileCrudRepository(File file) {
        this.file = file;
    }

    @Override
    public User add(User user) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(user.getID() + ";" + user.getName() + ";" + user.getAge());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteById(int id) {
        List<User> userList = getAll();

        try {
            if (getById(id).getID() == id) {
                userList.remove(getById(id));
                save(userList);
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public User update(User updateWith) {
        List<User> userList = getAll();

        try {
            for (User user : userList) {
                if (getById(user.getID()).getID() == updateWith.getID()) {
                    user.setName(updateWith.getName());
                    user.setAge(updateWith.getAge());
                    save(userList);
                }
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<User> save(List<User> userList) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (User user : userList) {
                bw.write(user.getID() + ";" + user.getName() + ";" + user.getAge());
                bw.flush();
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<User> getAll() {
        String str;
        List<User> userList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((str = br.readLine()) != null) {
                String[] cols = str.split(";");

                int id = Integer.parseInt(cols[0]);
                String name = cols[1];
                int age = Integer.parseInt(cols[2]);

                userList.add(new User(id, name, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getById(int id) throws UserNotFoundException {
        List<User> userList = getAll();

        for (User user : userList) {
            if (user.getID() == id) {
                return user;
            }
        }
        throw new UserNotFoundException("User with id: " + id + " not found.");
    }
}
