package ru.neoflex.sender.Repository;

import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.neoflex.sender.Model.User;

@Repository
public class UserReactiveRepo {
    private ReactiveMongoTemplate template;

    public UserReactiveRepo(ReactiveMongoTemplate template) {
        this.template = template;
    }

    public Mono<User> findById(Long id) {
        return template.findById(id, User.class);
    }

    public Flux<User> findAll() {
        return template.findAll(User.class);
    }

    public Mono<User> save(Mono<User> account) {
        return template.save(account);
    }

    public Mono<DeleteResult> delete(Long id) {
        return template.remove(findById(id));
    }
}
