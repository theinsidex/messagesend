package ru.neoflex.sender.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.neoflex.sender.Model.User;
import ru.neoflex.sender.Repository.UserRepo;
import ru.neoflex.sender.Service.UserService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class UserRestControllerTest {
    @InjectMocks
    private UserRestController userController;
    @Mock
    private UserService userService;
    @MockBean
    private UserRepo userRepo;
    @Mock
    private User user;
    List<User> users;
    @Autowired
    private MockMvc mockMvc;
    @Before
    public void setup(){
//     user = new User();
//     user.setId(2);
//     user.setName("Ivan");
//     user.setEmail("Branchaev@bk.ru");
//     users=new ArrayList<>();
//     users.add(user);
//     userService.addUser(user);
    }

    @Test
    public void listUser() throws Exception {
        when(userController.listUsers()).thenReturn(Collections.emptyList());
        List<User> users = userController.listUsers();
        verify(userService).listUsers();

    }

    @Test
    public void addUser(){
        userController.addUser(user);
        verify(userService).insert(user);
        }

    @Test
    public void deleteUser(){
        userController.deleteUser(user.getId());
        verify(userService).remove(user.getId());
    }

}