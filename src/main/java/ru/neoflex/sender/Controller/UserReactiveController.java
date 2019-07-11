package ru.neoflex.sender.Controller;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.neoflex.sender.Model.User;
import ru.neoflex.sender.Service.UserReactiveService;

@RestController
@RequestMapping("/react")
public class UserReactiveController {
    @Autowired
    private UserReactiveService userService;

    @GetMapping("list")
    public Flux<User> listUsers(){
        return userService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public Mono<DeleteResult> delete(@PathVariable("id") long id)
    {
        return userService.delete(id);
    }

    @PostMapping
    public Mono<User> save(@RequestBody Mono<User> monoUser){
        return userService.insert(monoUser);
    }
}
