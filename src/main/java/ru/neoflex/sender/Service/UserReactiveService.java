package ru.neoflex.sender.Service;

import com.mongodb.bulk.DeleteRequest;
import com.mongodb.client.result.DeleteResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.neoflex.sender.Model.User;
import ru.neoflex.sender.Repository.UserReactiveRepo;

@Service
public class UserReactiveService {
    private UserReactiveRepo userRepo;

    public UserReactiveService(UserReactiveRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Mono<User> insert(Mono<User> user) {
        return userRepo.save(user);
    }

    public Mono<DeleteResult> delete(long id) {
        return userRepo.delete(id);
    }

    public Flux<User> findAll() {
        return userRepo.findAll();
    }
}
