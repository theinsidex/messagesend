package ru.neoflex.sender.Service;

import org.springframework.stereotype.Service;
import ru.neoflex.sender.Model.User;
import ru.neoflex.sender.Repository.UserRepoMDB;

import java.util.List;


@Service
public class UserService {
    private UserRepoMDB userRepo;

    public UserService(UserRepoMDB userRepo){
        this.userRepo=userRepo;
    }

    public void insert(User user){

        userRepo.insert(user);
    }
    public List<User> listUsers(){
        return userRepo.findAll();
    }

    public User getUser(long id){
        User user = userRepo.findById(id);
        return user;
    }

    public void remove(long id){
        userRepo.remove(id);
    }
}
