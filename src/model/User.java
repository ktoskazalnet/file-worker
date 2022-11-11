package model;

import java.util.Objects;

public class User {
    private final int ID;
    private String name;
    private int age;

    public User(int id) {
        ID = id;
    }

    public User(int ID, int age) {
        this.ID = ID;
        this.age = age;
    }

    public User(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public User(int id, String name, int age) {
        this.ID = id;
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Age must be > 0");
        }
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID == user.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + ID +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
