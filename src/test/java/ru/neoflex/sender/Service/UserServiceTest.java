package ru.neoflex.sender.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import ru.neoflex.sender.Controller.UserController;
import ru.neoflex.sender.Model.User;
import ru.neoflex.sender.Repository.UserRepo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepo userRepo;

    @Test
    public void addUser() {
        User user = new User();
        userService.insert(user);
        verify(userRepo).insert(user);
    }

    @Test
    public void listUsers() {
        userService.listUsers();
        verify(userRepo).findAll();
    }

    @Test
    public void getUser() {
        long id = 5;
        userService.getUser(id);
        verify(userRepo).findById(id);
    }

    @Test
    public void deleteUser() {
        long id = 5;
        userService.remove(id);
        verify(userRepo).remove(id);
    }
}