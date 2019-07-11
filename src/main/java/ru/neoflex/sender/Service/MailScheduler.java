package ru.neoflex.sender.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.neoflex.sender.Model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailScheduler {

    private final String  SUBJECT = "Чайная пауза";
    private final String  TEXT = "Пора пить чай!";

    private MailSender mailSender;
    private UserService userService;

    @Autowired
    public MailScheduler(MailSender mailSender,UserService userService){
        this.mailSender = mailSender;
        this.userService = userService;
    }

    @Scheduled(cron ="${app.cron}" )
    public void sendMessage(){
        List<User> usersWithEmail = checkUsers();
        for(User user:usersWithEmail){
            mailSender.send(user.getEmail(),SUBJECT,TEXT);
        }
    }
    public List<User> checkUsers(){
        return userService.listUsers().stream() //проверка на наличие email
                .filter(x -> !x.getEmail().equals(null))
                .collect(Collectors.toList());
    }
}
