package ru.neoflex.sender.Controller;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import ru.neoflex.sender.Model.User;
import ru.neoflex.sender.Service.UserService;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Mock
    private Model model;
    @Test
    public void listUser() {
        when(userService.listUsers()).thenReturn(ImmutableList.of());
        userController.listUser(model);
        verify(userService).listUsers();
    }

    @Test
    public void delete() {
        long id = 5;
        userController.delete(id);
        verify(userService).remove(id);
    }

    @Test
    public void save(){
        User user = new User();
        userController.save(user);
        verify(userService).insert(user);
    }
}