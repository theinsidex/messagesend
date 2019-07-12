package ru.neoflex.sender.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neoflex.sender.Model.User;
import ru.neoflex.sender.Parser.CsvParser;
import ru.neoflex.sender.Service.MailScheduler;
import ru.neoflex.sender.Service.MailSender;
import ru.neoflex.sender.Service.UserService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MailSender mailSender;
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
//Получение списка всех пользователей
    @GetMapping("/list")
    public String listUser(Model model) throws IOException {
        List<User> users = userService.listUsers();
        CsvParser parser = new CsvParser();
        model.addAttribute("users",users);
        return "user/list";
    }
//Добавление пользователя
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("user",new User());
        return "user/form";
    }
//Сохранение пользователя
    @PostMapping("/save")
    public String save(User user)
    {
        userService.insert(user);
        MailScheduler mailScheduler = new MailScheduler(mailSender,userService);
        mailScheduler.sendMessage();
        return "redirect:list";
    }
//Удаление пользователя
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id){
        userService.remove(id);
        return "redirect:/user/list";
    }
}
