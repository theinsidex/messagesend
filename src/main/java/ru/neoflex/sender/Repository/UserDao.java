package ru.neoflex.sender.Repository;

import ru.neoflex.sender.Model.User;

import java.util.List;

public interface UserDao {
    void insert(User user);
    List<User> findAll();
    User findById(long id);
    void remove(long id);
}
