package com.user.repository;

import com.user.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserFileCrudRepository implements UserCrudRepository {
    private final File file;

    public UserFileCrudRepository(File file) {
        this.file = file;
    }

    @Override
    public User add(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            int currentId = User.getAndIncrementCurrentId();
            user.setId(currentId);

            bw.write(currentId + ";" + user.getName() + ";" + user.getAge());
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

        Optional<User> byId = getById(id);

        if (byId.isPresent()) {
            User user = byId.get();
            userList.remove(user.getId());
            save(userList);
            return true;
        }

        return false;
    }

    @Override
    public User update(User updateWith) {
        List<User> userList = getAll();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).equals(updateWith)) {
                userList.set(i, updateWith);
                save(userList);
            }
        }

        return updateWith;
    }

    private List<User> save(List<User> userList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (User user : userList) {
                bw.write(user.getId() + ";" + user.getName() + ";" + user.getAge());
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

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
    public Optional<User> getById(int id) {
        List<User> userList = getAll();

        for (User user : userList) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
