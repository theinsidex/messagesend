package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.neoflex.sender.Service.UserService;

import static org.mockito.Mockito.mock;

@Component
public class TestConfig {

    @Bean
    public UserService userService(){
        return mock(UserService.class);
    }

}
