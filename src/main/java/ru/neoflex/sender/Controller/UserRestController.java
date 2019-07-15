package ru.neoflex.sender.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.sender.Model.User;
import ru.neoflex.sender.Service.UserService;

import java.util.List;


@RestController
@RequestMapping("/rest")
public class UserRestController {
    private UserService userService;

    public UserRestController(UserService userService){
        this.userService=userService;
    }
//Список пользователелй
    @RequestMapping(value = "/list",
                    method = RequestMethod.GET,
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<User> listUsers() {
       return userService.listUsers();
    }
//Добавление пользователя
    @RequestMapping(value = "/add",
                    method = RequestMethod.POST,
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void addUser(@RequestBody User user) {
        userService.insert(user);
    }
//Удаление пользователя
    @RequestMapping(value = "/delete/{id}",
                    method = RequestMethod.DELETE,
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteUser(@PathVariable("id") long id ){
        userService.remove(id);
    }
}
