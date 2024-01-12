package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        // Inizializza il repository con un utente
        User user = new User("stefano", "1234");
        User user2 = new User("laura", "5678");
        userRepository.save(user);
        userRepository.save(user2);
    }
}

